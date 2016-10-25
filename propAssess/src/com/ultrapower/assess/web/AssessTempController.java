package com.ultrapower.assess.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.saxon.exslt.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.xpath.operations.String;
import org.hibernate.mapping.Set;
import org.mozilla.javascript.ObjToIntMap.Iterator;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.bidlink.core.commons.BaseManageController;
import com.bidlink.core.commons.support.page.Page;
import com.ibm.icu.text.SimpleDateFormat;
import com.ultrapower.accredit.rmiclient.RmiClientApplication;
import com.ultrapower.accredit.util.GetUserUtil;
import com.ultrapower.assess.manager.AssessObjManager;
import com.ultrapower.assess.manager.AssessRecordsManager;
import com.ultrapower.assess.manager.AssessResultManager;
import com.ultrapower.assess.manager.AssessScheduManager;
import com.ultrapower.assess.manager.AssessTemplateManager;
import com.ultrapower.assess.model.AssessObject;
import com.ultrapower.assess.model.AssessProp;
import com.ultrapower.assess.model.AssessRecords;
import com.ultrapower.assess.model.AssessResult;
import com.ultrapower.assess.model.AssessSchedu;
import com.ultrapower.assess.model.AssessTemplate;
import com.ultrapower.assess.util.PageUtils;
import com.ultrapower.assess.util.WebUtil;

/**
 * 模板下发
 * 
 * @author biyu
 * 
 */
@SuppressWarnings("unchecked")
public class AssessTempController extends BaseManageController {

	private AssessTemplateManager assessTemplateManager;
	private AssessObjManager assessObjManager;
    private AssessRecordsManager assessRecordsManager;
	private AssessResultManager assessResultManager;
	private AssessScheduManager assessScheduManager;
    
	public void setAssessResultManager(AssessResultManager assessResultManager) {
		this.assessResultManager = assessResultManager;
	}

	public void setAssessRecordsManager(AssessRecordsManager assessRecordsManager) {
		this.assessRecordsManager = assessRecordsManager;
	}
	public void setAssessTemplateManager(
			AssessTemplateManager assessTemplateManager) {
		this.assessTemplateManager = assessTemplateManager;
	}

	public void setAssessObjManager(AssessObjManager assessObjManager) {
		this.assessObjManager = assessObjManager;
	}

	
	public void setAssessScheduManager(AssessScheduManager assessScheduManager) {
		this.assessScheduManager = assessScheduManager;
	}

	@Override
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("assess/frame/send_frame.jsp");
		return mav;
	}

	public ModelAndView sendleft(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("assess/frame/send_left.jsp");
		return mav;
	}

	public ModelAndView send(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("assess/frame/send.jsp");
		String isPage = request.getParameter("isPage");
		List<AssessObject> list = assessObjManager.getAllIsUseobj();
		Page pageObj = assessRecordsManager.getScheduName(request);
		List<AssessTemplate> templates = assessTemplateManager.findByStatus(2);
		mav.addObject("assessObj", list);
		mav.addObject("anlist", pageObj.getResult());
		mav.addObject("templates", templates);
		mav.addObject("isPage",isPage);
		mav.addObject("pageToString", PageUtils.showPage5(pageObj,
				"assessTemp.do?method=send&isPage=2"));
		       
		return mav;
	}

	public ModelAndView deleteSchedu(HttpServletRequest request, // 刪除考核
			HttpServletResponse response) throws Exception {

		String sid = request.getParameter("id");
		assessScheduManager.get(sid).setState(1l);
		try {
			assessScheduManager.save(assessScheduManager.get(sid));
			request.setAttribute("divtype", "2");
		} catch (Exception e) {
			request.setAttribute("divtype", "1");
		}
		return this.send(request, response);
	}

	public ModelAndView sendRecorder(HttpServletRequest request, // 下发记录
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("assess/frame/sendRecorder.jsp");
		Page pageObj = assessRecordsManager.getSendRecordObj(request);
		
		if(RmiClientApplication.getInstance().hasPrivilege(GetUserUtil.getUsername(), "ASSESS", "ASSESS010301", "P002")){
			mav.addObject("candelete", "true");
		}else{
			mav.addObject("candelete", "false");
		}
		mav.addObject("rlist", pageObj.getResult());
		mav.addObject("pageToString", PageUtils.showPage5(pageObj,
				"assessTemp.do?method=sendRecorder"));
		return mav;
	}
    /*
     * 下发记录删除
     */
	
	public ModelAndView deleteRecores(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		@SuppressWarnings("unused")
	
		RmiClientApplication rmi= RmiClientApplication.getInstance();
		boolean i=rmi.hasPrivilege(GetUserUtil.getUsername(), "ASSESS", "ASSESS010301", "P002");
		
	
		
		
		ModelAndView mav = new ModelAndView("assess/frame/sendRecorder.jsp");
		String chkIds = request.getParameter("chkIds");
		String[] chkIdes =  chkIds.split(",");
		int success = 0;
		String msg = WebUtil.getMessages(request, response);
		if (StringUtils.isNotBlank(chkIds)) {
			for (String id : chkIdes) {
				AssessRecords assessRecords = assessRecordsManager.get(id);
				assessRecords.setState(2l);// 设置为删除
				Set<AssessResult> resultset = new LinkedHashSet<AssessResult>();
				resultset = assessRecords.getAssessResults();
				Iterator result=resultset.iterator();
				try{
					while(result.hasNext()){
						AssessResult assessResult= new AssessResult();
						assessResult=(AssessResult)result.next();
						assessResult.setState(2l);
						System.out.println(assessResult.getId());
						assessResultManager.save(assessResult);
					}
				}catch (Exception e){
					e.printStackTrace();
				}
				assessRecordsManager.save(assessRecords);
				success++;
			}
		}
		if (StringUtils.isBlank(msg)) {
			msg = "成功更新" + success + "条纪录!";
		}
		
		return this.sendRecorder(request, response);
	}
	public ModelAndView addAssess(HttpServletRequest request, // 已有考核新增考核对象
			HttpServletResponse response) throws Exception {
		// List<AssessObject> list =assessObjManager.getAobj();
		// String objectName = request.getParameter("objectName"); //
		// 考核对象名称，可有多个考核对象
		String tid = request.getParameter("assessTempId");
		String scheduleId = request.getParameter("an"); // 考核schedu的id
		String id = request.getParameter("id");
		String assessobj[] = request.getParameterValues("objectCode");
		AssessSchedu assessSchedu = null;
		String msg = "";
		String message = "";
		if (StringUtils.isBlank(id)) {
			for (int i = 0; i < assessobj.length; i++) {
				Set<AssessResult> resultset = new LinkedHashSet<AssessResult>();
				AssessRecords assessrecords = new AssessRecords();
				Set<AssessProp> propset = new LinkedHashSet<AssessProp>();
				BindingResult result = bindObject(request, assessrecords);
				if (result.hasErrors()) {
					msg = result.getAllErrors().toString();
					System.out.println(msg);
				} else {//
					if (!assessRecordsManager.getHadRedordByObj(scheduleId,
							assessobj[i])) { // 判断考核对象是否已经存在该模板的下发记录(一个考核中，没个考核对象只能有一条记录)
						assessSchedu = assessScheduManager.get(scheduleId);

						assessrecords.setAssessObject(assessObjManager
								.getAssessObjectById(assessobj[i]));
						assessrecords.setAssessTemplate(assessTemplateManager
								.get(tid));
						assessrecords.setPeriod(assessSchedu.getPerior());
						assessrecords.setResultcreatTime(assessSchedu
								.getResultcreatetime());
						propset = assessTemplateManager.get(tid)
								.getAssessProps(); // 得到模板的多个指标
						System.out.println(propset.size()); // 放入Set
						assessrecords.setBeginTime(assessSchedu.getBegintime());
						assessrecords.setEndTime(assessSchedu.getEndtime());
						assessrecords.setCreateTime(new Date());
						assessrecords.setReleaseName(GetUserUtil.getRealname());
						assessrecords.setAssessTimes(0l);
						assessrecords.setAssessSchedu(assessScheduManager
								.get(scheduleId));
						assessrecords.setScheduName(assessSchedu.getName());
						Iterator propit = propset.iterator(); // 循环将porp放入result对象
						assessRecordsManager.save(assessrecords);
						try {
							while (propit.hasNext()) {
								AssessResult ar = new AssessResult();
								ar.setAssessProp((AssessProp) propit.next());
								ar.setAssessRecords(assessrecords);
								ar.setAssessRecords(assessrecords); // 将result放入set
								assessResultManager.save(ar);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						assessrecords.setAssessResults(resultset);

					} else {
						// TODO 通知页面
						message = message
								+ assessObjManager.get(assessobj[i])
										.getObjectName() + "已存在记录;";
						System.out.println("该考核对象已有该模板的下发记录，不再录入数据库");
					}
				}
			}
		}
		request.setAttribute("scheduid", scheduleId);
		request.setAttribute("tempId", tid);
		request.setAttribute("message", message);
		return this.sendRecorder(request, response);
	}

	public ModelAndView addassessRecords(HttpServletRequest request, // 新增考核项
			HttpServletResponse response) throws Exception {
		AssessRecords assessreCords = new AssessRecords();
		BindingResult result = bindObject(request, assessreCords);
		String tid = request.getParameter("assessTempId");
		String id = request.getParameter("id");
		String assessobj[] = request.getParameterValues("objectCode");
		java.util.Date beginTime = null;
		java.util.Date endTime = null;
		java.util.Date resultcteateTime = null;
		String msg = "";
		AssessSchedu assessSchedu = null;
		if (StringUtils.isBlank(id)) {
			assessSchedu = new AssessSchedu(); // 新建考核计划对象
			// 用于保存新建考核计划
			if (result.hasErrors()) {
				msg = result.getAllErrors().toString();
				System.out.println(msg);
			} else {
				String bTime = request.getParameter("beginTime");
				String eTime = request.getParameter("endTime");
				String rcTime = request.getParameter("resultcreatTime");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				try {
					beginTime = (java.util.Date) sdf.parse(bTime);
					endTime = (java.util.Date) sdf.parse(eTime);
					resultcteateTime = (java.util.Date) sdf.parse(rcTime);
					System.out.println(sdf.format(beginTime));
				} catch (ParseException e) {
					e.printStackTrace();
				}

				assessSchedu.setBegintime(beginTime);
				assessSchedu.setEndtime(endTime);
				assessSchedu.setCreatetime(new Date());
				assessSchedu.setName(request.getParameter("scheduName"));
				assessSchedu.setPerior(Long.valueOf(request
						.getParameter("period")));
				assessSchedu.setState(0L);
				assessSchedu.setResultcreatetime(resultcteateTime);
				assessSchedu.setType(0L);
				assessScheduManager.save(assessSchedu); // 保存新考核记录

			}
			for (int i = 0; i < assessobj.length; i++) { // 循环对每个考核对象在records表里插入一条数据
				AssessRecords assessrecords = new AssessRecords();
				assessrecords.setAssessObject(assessObjManager
						.get(assessobj[i]));
				assessrecords.setAssessTemplate(assessTemplateManager
						.getAssessTemplateById(tid));
				assessrecords.setAssessSchedu(assessSchedu);
				assessrecords.setBeginTime(assessSchedu.getBegintime());
				assessrecords.setEndTime(assessSchedu.getEndtime());
				assessrecords.setCreateTime(assessSchedu.getCreatetime());
				assessrecords.setPeriod(assessSchedu.getPerior());
				assessrecords.setResultcreatTime(assessSchedu
						.getResultcreatetime());
				assessrecords.setReleaseName(GetUserUtil.getRealname());
				assessrecords.setScheduName(assessSchedu.getName());
				Set<AssessResult> resultset = new LinkedHashSet<AssessResult>();
				Set<AssessProp> propset = new LinkedHashSet<AssessProp>();
				propset = assessTemplateManager.get(tid).getAssessProps();
				Iterator propit = propset.iterator();
				assessRecordsManager.save(assessrecords);
				try {
					while (propset.iterator().hasNext()) {
						AssessResult ar = new AssessResult();
						ar.setAssessProp((AssessProp) propit.next());
						ar.setAssessRecords(assessrecords);
						ar.setAssessRecords(assessrecords); // 将result放入set
						assessResultManager.save(ar);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				assessrecords.setAssessResults(resultset);
				//assessRecordsManager.save(assessrecords);
			}
		}
		return this.sendRecorder(request, response);
	}

	
}
