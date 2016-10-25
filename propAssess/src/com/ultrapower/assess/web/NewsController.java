package com.ultrapower.assess.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.saxon.exslt.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.xpath.operations.String;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.bidlink.core.Constants;
import com.bidlink.core.commons.BaseManageController;
import com.bidlink.core.commons.support.page.Page;
import com.bidlink.core.utils.DateUtil;
import com.ultrapower.assess.contants.ChdConstants;
import com.ultrapower.assess.manager.NewsManager;
import com.ultrapower.assess.model.MessageModel;
import com.ultrapower.assess.model.News;
import com.ultrapower.assess.model.Users;
import com.ultrapower.assess.util.FileUtil;
import com.ultrapower.assess.util.PageUtils;
import com.ultrapower.assess.util.WebUtil;

public class NewsController extends BaseManageController {

	private NewsManager newsManager;

	public void setNewsManager(NewsManager newsManager) {
		this.newsManager = newsManager;
	}
	

	/**
	 * 前台新闻动态首页
	 */
	@Override
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("index/news.jsp");
		// 获取新闻
		List<News> lst = newsManager.getNewsList();
		mav.addObject("newsList", lst);
		return mav;
	}

	/**
	 * 前台新闻动态首页
	 */
	public ModelAndView updateSeq(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获取新闻
		String seq = request.getParameter("seq");
		Long type = Long.valueOf(request.getParameter("type"));
		Long id = Long.valueOf(request.getParameter("id"));
		News news = newsManager.get(id);
		if (seq.equals("up")) {
			// 获得上一条新闻
			News upNews = newsManager.getUpNews(news.getSeq(), type);
			if (upNews != null) {
				int seq1 = news.getSeq();
				news.setSeq(upNews.getSeq());
				newsManager.save(news);// 将当前新闻的序号改为上一条的序号
				upNews.setSeq(seq1);
				newsManager.save(upNews);// 将上一条新闻的排序号改为当前新闻的序号
			}
		} else {
			News downNews = newsManager.getDownNews(news.getSeq(), type);
			if (downNews != null) {
				int seq1 = downNews.getSeq();
				downNews.setSeq(news.getSeq());
				newsManager.save(downNews);// 将下一条新闻的排序号改为当前新闻的序号
				news.setSeq(seq1);
				newsManager.save(news);// 将当前新闻的序号改为下一条的序号
			}
		}
		return new ModelAndView("redirect:/adminNews.do?method=list&type="
				+ type + "&status=0");
	}


	/**
	 * 新闻列表
	 */
	@Override
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("admin/news/list.jsp");
		try {
			int pageNo = PageUtils.populatePageInfoFromRequest(request);
			Long type = new Long(request.getParameter("type"));
			String hql = "from News where type=" + type;
			Integer stauts = new Integer(request.getParameter("status"));
			hql += " and status = " + stauts;
			String title = request.getParameter("title");
			String source = request.getParameter("docSource");
			if (StringUtils.isNotBlank(title)) {
				hql += " and title like '%" + title + "%'";
			}
			if (StringUtils.isNotBlank(source)) {
				hql += " and docSource like '%" + source + "%'";
			}
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			if (StringUtils.isNotBlank(startTime)) {
				startTime = startTime + " 00:00:00";
				hql += "and updateDate >= date_format('" + startTime
				+ "', '%Y-%c-%d %h:%i:%s')";
			}
			if (StringUtils.isNotBlank(endTime)) {
				endTime = endTime + " 23:59:59";
				hql += "and updateDate <= date_format('" + endTime
				+ "', '%Y-%c-%d %h:%i:%s')";
			}
			hql += " order by seq desc";
			Page page = newsManager.pagedQuery(hql, pageNo,
					Constants.DEFAULT_PAGE_SIZE);
			mav.addObject("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	/**
	 * 去新增信息
	 */
	@Override
	public ModelAndView create(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("admin/news/edit.jsp");
		return mav;
	}

	/**
	 * 新增信息
	 */
	@Override
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView("admin/news/success.jsp");
		String msg = "";
		String id = request.getParameter("id");
		Users user = (Users) request.getSession().getAttribute(
				Constants.USER_IN_SESSION);
		// 新增的情况
		if (StringUtils.isBlank(id)) {
			News news = new News();
			BindingResult result = bindObject(request, news);
			if (result.hasErrors()) {
				msg = result.getAllErrors().toString();
			} else {
				news.setCreateDate(new Date());
				news.setCreateUser(user);
				news.setUpdateUser(user);
				if (news.getUpdateDate() == null) {
					news.setUpdateDate(new Date());
				}
				String path = FileUtil.saveUploadWebFile(request, "url",
						"yzImage");
				if (StringUtils.isNotBlank(path)) {
					String url = ChdConstants.HTTP_PATH + "/upload/files/"
							+ path;
					//System.out.println("url:" + url);
					news.setImageUrl(url);
				}
				news.setIsYZ(0l);
				news.setStatus(0);
				news.setCount(1);
				newsManager.save(news);
//				news.setSeq(news.getId().intValue());
				newsManager.save(news);
				msg = "数据操作成功!";
			}
		} else {// 修改的情况
			News news = newsManager.get(Long.valueOf(id));
			news.setTitle(request.getParameter("title"));
			news.setContent(request.getParameter("content"));
			news.setDocSource(request.getParameter("docSource"));
			String date = request.getParameter("updateDate");
			news.setUpdateDate(DateUtil.parse(date));
			String path = FileUtil.saveUploadWebFile(request, "url", "yzImage");
			if (StringUtils.isNotBlank(path)) {
				String url = ChdConstants.HTTP_PATH + "/upload/files/" + path;
				news.setImageUrl(url);
			}
			news.setUpdateUser(user);
			newsManager.save(news);
			msg = "数据操作成功!";

		}
		mav.addObject("msg", msg);
		return mav;
	}

	/**
	 * 去修改信息
	 */
	@Override
	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("admin/news/edit.jsp");
		News news = newsManager.get(Long.valueOf(request.getParameter("id")));
		mav.addObject("news", news);
		return mav;
	}

	/**
	 * 删除信息
	 */
	public ModelAndView changeStatus(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String[] chkIds = request.getParameterValues("chkIds");
		int success = 0;
		String msg = WebUtil.getMessages(request, response);
		String status = request.getParameter("status");
		if (chkIds != null) {
			for (String id : chkIds) {
				News news = newsManager.get(Long.valueOf(id));
				news.setStatus(Integer.valueOf(status));// 设置为删除
				newsManager.save(news);
				success++;
			}
		}
		if (StringUtils.isBlank(msg)) {
			msg = "成功更新" + success + "条纪录!";
		}
		this.renderJson(response, JSONObject.fromObject(
				new MessageModel(true, msg, null)).toString());
		return null;
	}
	

}
