package com.ultrapower.assess.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xpath.operations.String;
import org.hibernate.mapping.Map;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bidlink.core.commons.BaseManageController;
import com.bidlink.core.commons.support.page.Page;
import com.ultrapower.accredit.util.GetUserUtil;
import com.ultrapower.assess.manager.AssessChargeManager;
import com.ultrapower.assess.manager.AssessPropManager;
import com.ultrapower.assess.manager.AssessRecordsManager;
import com.ultrapower.assess.manager.AssessResultManager;
import com.ultrapower.assess.model.AssessRecords;
import com.ultrapower.assess.model.AssessResult;
import com.ultrapower.assess.model.AssessTemplate;
import com.ultrapower.assess.util.FileUtil;
import com.ultrapower.assess.util.PageUtils;
import com.ultrapower.assess.util.ServletUtils;

/**
 * 我的待办
 * 
 * @author admin
 * 
 */
@SuppressWarnings("unchecked")
public class MyChargeController extends BaseManageController {

	private AssessChargeManager assessChargeManager;
	private AssessRecordsManager assessRecordsManager;
	private AssessPropManager assessPropManager;
	private AssessResultManager assessResultManager;

	public void setAssessChargeManager(AssessChargeManager assessChargeManager) {
		this.assessChargeManager = assessChargeManager;
	}
	public void setAssessRecordsManager(AssessRecordsManager assessRecordsManager) {
		this.assessRecordsManager = assessRecordsManager;
	}
	
	public void setAssessPropManager(AssessPropManager assessPropManager) {
		this.assessPropManager = assessPropManager;
	}

    public void setAssessResultManager(AssessResultManager assessResultManager) {
		this.assessResultManager = assessResultManager;
	}
	/**
     * 去我的代办列表页面
     */
	@Override
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("assess/frame/content_myWork.jsp");
		Page pageObj = assessRecordsManager.getRecordPageByAssignee(GetUserUtil.getUsername(),request);
		mav.addObject("recordsList", pageObj.getResult());
		mav.addObject("pageToString", PageUtils.showPage5(pageObj, "assessProxy.do?method=index"));
		return mav;
	}
	public ModelAndView view(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView("assess/frame/contentPage.jsp");
		String recordsId = request.getParameter("recordsId");
		String page = request.getParameter("page");
		if(page!=null&&page.equals("result")){
			mav.setViewName("assess/frame/resultContentPage.jsp");
		}else{
			mav.setViewName("assess/frame/contentPage.jsp");
		}
		int maxColspan = 1;
		String htmlToString = "";
		AssessTemplate assessTemplate = null;
		
		if(StringUtils.isNotBlank(recordsId)){
			AssessRecords assessRecords = assessRecordsManager.get(recordsId);
			assessTemplate = assessRecords.getAssessTemplate();
			List<String[]> propList = assessPropManager.getPropList(assessTemplate.getId());
	        maxColspan = assessPropManager.getMaxColspan(propList);
	        Map<String,AssessResult> resultMap = assessResultManager.getResultMap(recordsId);
	        htmlToString = assessChargeManager.htmlToString(maxColspan,assessRecords.getAssessTotal(),propList,resultMap,page);
		}
		mav.addObject("assessTemplate",assessTemplate);
		mav.addObject("htmlToString", htmlToString);
		mav.addObject("colspan", maxColspan);
		mav.addObject("recordsId", recordsId);
		mav.addObject("page", page);
		mav.addObject("CanModifyResult", assessRecordsManager.CanModifyResult(recordsId));
		return mav;
	}
	
	
	/**
	 * 保存模板
	 * 
	 * @author wangjing
	 */
	@Override
	public ModelAndView save(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("assess/frame/checkKpiValResult.jsp");
		String kpiValues = request.getParameter("kpiValues");
		String resultIds = request.getParameter("resultIds");
        String recordsId = request.getParameter("recordsId");
        AssessRecords assessRecords = assessRecordsManager.get(recordsId);
        String msg = assessResultManager.saveResults(assessRecords,kpiValues,resultIds);
        mav.addObject("msg", msg);
        return mav;
	}
	
	public ModelAndView topage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String recordsId = request.getParameter("recordsId");
		String page = request.getParameter("page");
		Workbook wb = new XSSFWorkbook();
		String excelname = "考核.xlsx";
		AssessTemplate assessTemplate = null;
		if(StringUtils.isNotBlank(recordsId)){
			assessTemplate = assessRecordsManager.get(recordsId).getAssessTemplate();
			Map<String,AssessResult> resultMap = assessResultManager.getResultMap(recordsId);
			wb = assessChargeManager.exportExcel(page,assessTemplate,resultMap);
			excelname = assessTemplate.getTemplateName()+".xlsx";
		}
		//TODO 查询代办生成excel
		response.setContentType(ServletUtils.EXCEL_TYPE);
		ServletUtils.setFileDownloadHeader(response, excelname);

		wb.write(response.getOutputStream());
		response.getOutputStream().flush();
		return null;

	}

	public ModelAndView toupload(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("assess/frame/upload.jsp");
		String recordsId = request.getParameter("recordsId");
		mav.addObject("recordsId", recordsId);
		return mav;
	}
	public ModelAndView upload(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("assess/frame/upload.jsp");
		// 上传图片并获取路径
		//TODO 解析excel 更新result表中指标值；result表中记录在下发模板时已经生成，此处注意只更新；
		String recordsId = request.getParameter("recordsId");
		String msg = readExcel(request, "file",recordsId);
		FileUtil.saveUploadWebFile(request, "file", "excel");
		//TODO 日志文件记录好文件的上传人 上传时间 保存的文件名等信息，以备核查数据
		mav.addObject("msg", msg);
		mav.addObject("recordsId", recordsId);
		return mav;   
	}

	public String readExcel(HttpServletRequest request, String elementName,String recordsId) {
		String msg = "unknow";
		try {
			MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
			MultipartFile file = req.getFile(elementName);
			 if (file == null) {    
	                throw new Exception("上传失败：文件为空");      
	         } 
			InputStream inp = file.getInputStream();
			Workbook wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheetAt(0);
			String kpiValues = "";
			Row row1 = sheet.getRow(1);
			int LastCell = row1.getLastCellNum();
			int propCellNum = -1;
			for(int i=LastCell-1;i>0;i--){
				if(row1.getCell(i).toString().equals("指标值")){
					propCellNum = i;
					break;
				}
			}
			if(propCellNum==-1){
				msg = "noprop";
			}
			
			for(int i = 2;i<=sheet.getLastRowNum();i++){
				Row row = sheet.getRow(i);
				Cell cell = row.getCell(propCellNum);
				if(cell==null){
					return "isnull";
				}
				String value = cell.toString(); 
				if(value.length()>=2&&value.substring(value.length()-2, value.length()).equals(".0")){
					value = value.substring(0, value.length()-2);
				}
				if(i==2){
					kpiValues = value;
				}else{
					kpiValues += "," + value;
				}
			}
	        AssessRecords assessRecords = assessRecordsManager.get(recordsId);
	        msg = assessResultManager.saveResults(assessRecords,kpiValues);
			
//			System.out.println(cell1.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
}
