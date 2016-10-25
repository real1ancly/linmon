package com.ultrapower.assess.security;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.xpath.operations.String;

import com.ultrapower.accredit.rmiclient.RmiClientApplication;

/**
 * 初始化权限资源
 * 
 * 第一次启动会加载一次，重新启动不会加载
 * 
 * 重新部署会加载一次
 * 
 * @author cws
 * 
 */
@SuppressWarnings("serial")
public class InitSecurity extends HttpServlet {

	private Logger log = Logger.getLogger(InitSecurity.class);

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			ServletContext sec = config.getServletContext();

			String spath = sec.getInitParameter("initsec");

			Properties p = new Properties();

			InputStream input = sec.getResourceAsStream(spath);

			p.load(input);

			String flag = p.getProperty("isinit");

			if (flag.equals("true")) {
				int row = RmiClientApplication.getInstance().initResourceByApp(
						"ASSESS");

				if (row == 1) {
					log.info("security is init failing!");
				} else {
					log.info("security is init successfully!");
				}
				String lpath = sec.getRealPath(spath);
				OutputStream out = new FileOutputStream(lpath);
				String cv = (String) p.setProperty("isinit", "false");
				p.store(out, cv);
				input.close();
				out.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
