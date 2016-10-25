package com.ultrapower.assess.manager;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xpath.operations.String;
import org.hibernate.mapping.Map;
import org.hibernate.mapping.Set;

import com.bidlink.core.commons.BaseHibernateDao;
import com.ultrapower.assess.model.AssessProp;
import com.ultrapower.assess.model.AssessResult;
import com.ultrapower.assess.model.AssessTemplate;

@SuppressWarnings("unchecked")
public class AssessChargeManager extends BaseHibernateDao {
	private AssessPropManager assessPropManager;
	public void setAssessPropManager(AssessPropManager assessPropManager) {
		this.assessPropManager = assessPropManager;
	}

	public List getList(String userName, String startCount, String endCount) {
		String sql = "select * from (select rownum rn, c.id,c.template_name,a.period,a.begin_time from assess_records a, assess_object b, ASSESS_TEMPLATE c where a.object_id = b.id  and a.template_id = c.id and b.assignee = '" + userName + "') where rn > " + startCount + " and  rn <=" + endCount;
		List lit = this.getSession().createSQLQuery(sql).list();
		return lit;
	}

	public List getCount(String userName) {
		String sql = "select count(*) from (select rownum rn, c.id, c.template_name, a.period, a.begin_time from assess_records a, assess_object b, ASSESS_TEMPLATE c where a.object_id = b.id and a.template_id = c.id and b.assignee = '" + userName + "')";
		List lit = this.getSession().createSQLQuery(sql).list();
		return lit;
	}
	
	/**
	 * 生成模板填写页面
	 * @param assessTemplate
	 * @return
	 */
	public String htmlToString(int maxColspan,Double total,List<String[]> propList,Map<String,AssessResult> resultMap,String page){
		int rowspan = 1;
		Set<String> idSet = new HashSet<String>();
		StringBuffer htmlBuffer = new StringBuffer();
		if(page==null){
			page="edit";
		}
		 for(int i=0;i<propList.size();i++){
			 int countCol = 0;
			 String[] props = propList.get(i);
			 String[] ids = props[0].substring(1).split("/");
			 String[] names = props[1].substring(1).split("/");
			 if(!idSet.contains(ids[0])){
				 rowspan = assessPropManager.countRowspan(propList, ids[0]);
				 htmlBuffer.append("<tr><td align='center' rowspan='"+rowspan+"'>")
				           .append(props[2].split("/")[1])
				           .append("</td>"); 
			 } 
			 
			 for(int j=0;j<names.length;j++){
				 if(j==names.length-1){
					 htmlBuffer.append("<td colspan='"+(maxColspan-countCol)+"'>")
					           .append(names[j])
				               .append("</td>");
				 }else{
					 if(!idSet.contains(ids[j])){
						 htmlBuffer.append("<td rowspan='"+assessPropManager.countRowspan(propList, ids[j])+"'>")
						           .append(names[j])
					               .append("</td>");
					 } 
					 countCol++;
				 }
			 }
			 //设置kpivalue 与score
			 htmlBuffer.append("<td  width='100px'>");
			 String endId = ids[ids.length-1];
			 AssessProp assessProp = assessPropManager.get(endId);
			 String kpivalue = "";
			 String score = "<td width='100px'>&nbsp</td>";
             if(page.equals("toedit")){
				    if(resultMap.containsKey(endId)){
						 AssessResult result =resultMap.get(endId);
						 kpivalue = "<input type='hidden' name='resultId' value='"+result.getId()+"'/>";
						 if(assessProp.getPropType()==1){
							 String kv = result.getKpiValue();
							 if(kv==null){
								kv=""; 
							 }
							 kpivalue += "<input type='text' name='kpiValue' id='kpiValue' maxlength='100' value='"+kv+"'>";
						 }else{
							 kpivalue += "<input type='hidden' name='kpiValue' id='kpiValue' value=''>自动获取";
						 }
					 }else{
						 kpivalue = "<input type='hidden' name='resultId' value=''/>";
						 if(assessProp.getPropType()==1){
							 kpivalue += "<input type='text' name='kpiValue' id='kpiValue' maxlength='100' value=''>";
						 }else{
							kpivalue += "<input type='hidden' name='kpiValue' id='kpiValue' value=''>自动获取";
						 }
					 }
			 }else{
				 kpivalue = "&nbsp;";
				 if(resultMap.containsKey(endId)){
					 AssessResult result = resultMap.get(endId);
					 if(result.getKpiValue()!=null){
						 kpivalue = result.getKpiValue();
						 score = "<td>"+result.getScore()+"</td>";
				     }else{
				    	 if(page.equals("result")){
				    		 kpivalue = "&nbsp;";
				    	 }else{
				    		 if(result.getAssessProp().getPropType()==2){
					    		 kpivalue = "自动获取";
					    	 }
				    	 }
				     }
			    }
			 }
			 htmlBuffer.append(kpivalue)
			           .append("</td><td>").append(props[3]).append("</td>");
			 if(!page.equals("result")){
				 htmlBuffer.append("<td>").append(props[4]).append("</td>");
			 }
			     htmlBuffer.append(score)
			               .append("</tr>");
			 for(String id:ids){
				 idSet.add(id); 
			 }
		 }
		 if(page.equals("result")){
		     htmlBuffer.append("<tr><td  style='font-weight:bold;text-align:right; font-size:14px;height:25px;' colspan='"+(maxColspan+4)+"'>总分:"+total+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>");
		 }
		 
		
		 return htmlBuffer.toString();
	}
	
	public Workbook exportExcel(String page,AssessTemplate assessTemplate,Map<String,AssessResult> resultMap){
		if(page==null){
			page="view";
		}
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("考核模板");
		CellStyle cs = wb.createCellStyle();
		sheet.createFreezePane(0, 1);

		sheet.autoSizeColumn(0, true);
		sheet.autoSizeColumn(1, true);
		sheet.autoSizeColumn(2, true);
		sheet.autoSizeColumn(3, true);
		sheet.autoSizeColumn(4, true);
		sheet.autoSizeColumn(5, true);
		
		int maxColspan = 1;
		List<String[]> propList = assessPropManager.getPropList(assessTemplate.getId());
	    maxColspan = assessPropManager.getMaxColspan(propList);
	    //产生标题
		 generateTitle(page,sheet,cs,maxColspan,assessTemplate);
		//产生表头
		 generateHeader(page,sheet,maxColspan);
		//产生内容
		 generateContent(page,sheet,maxColspan,propList,resultMap);
		sheet.autoSizeColumn((short) 2);
			return wb;
	}
	/**
	 * 生成标题头
	 * @param s
	 */
	private void generateTitle(String page,Sheet sheet,CellStyle cs,int maxColspan,AssessTemplate assessTemplate) {
		Row row0 = sheet.createRow(0);
		Cell cell0 = row0.createCell(0);
		cell0.setCellValue(assessTemplate.getTemplateName());
		cs.setAlignment(CellStyle.ALIGN_CENTER);
		// increase row height to accomodate two lines of text
		row0.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
		CellRangeAddress range = null;
		if(page.equals("result")){
			range = new CellRangeAddress(0,0, 0,maxColspan+3);
		}else{
			range = new CellRangeAddress(0,0, 0,maxColspan+2);
		}
		sheet.addMergedRegion(range);
		cell0.setCellStyle(cs);
	}
	
	/**
	 * 生成表头
	 * @param s
	 */
	private void generateHeader(String page,Sheet sheet,int maxColspan) {
		Row row1 = sheet.createRow(1);
		Cell cell1 = row1.createCell(0);
		Cell cell2 = row1.createCell(1, maxColspan);
		Cell cell3 = row1.createCell(maxColspan+1);
		Cell cell4 = row1.createCell(maxColspan+2);
//		Cell cell5 = row1.createCell(maxColspan+3);
		
		cell1.setCellValue("序号");
		cell2.setCellValue("评价指标");
		cell3.setCellValue("指标值");
		cell4.setCellValue("单位");
//		cell5.setCellValue("得分计算公式");
		if(page.equals("result")){
			Cell cell6 = row1.createCell(maxColspan+3);
			cell6.setCellValue("得分");
		}
		CellRangeAddress range = new CellRangeAddress(1,1,1,maxColspan);
		sheet.addMergedRegion(range);
	}
	
	/**
	 * 生成内容
	 * @param s
	 */
	private void generateContent(String page,Sheet sheet,int maxColspan,List<String[]> propList,Map<String,AssessResult> resultMap) {
		int rowspan = 1;
		Set<String> idSet = new HashSet<String>();
		for(int i=0;i<propList.size();i++){
			Row row = sheet.createRow(i+2);
			 int countCol = 0;
			 String[] props = propList.get(i);
			 String[] ids = props[0].substring(1).split("/");
			 String[] names = props[1].substring(1).split("/");
			 
			 Cell cell_0 = row.createCell(0);
			 Cell cell_2 = row.createCell(maxColspan+1);
			 Cell cell_3 = row.createCell(maxColspan+2);
//			 Cell cell_4 = row.createCell(maxColspan+3);
			 Cell cell_5 = row.createCell(maxColspan+3);
			 if(!idSet.contains(ids[0])){
				 cell_0.setCellValue(props[2].split("/")[1]);
				 rowspan = assessPropManager.countRowspan(propList, ids[0]);
				 if(rowspan>1){
					 CellRangeAddress ranges = new CellRangeAddress(i+2,i+1+rowspan, 0,0);
					 sheet.addMergedRegion(ranges); 
				 }
			 }
			 
			 for(int j=0;j<names.length;j++){
				 Cell cell_1 = row.createCell(j+1);
				 cell_1.setCellValue(names[j]);
				 countCol++;
				 if(j==names.length-1){
					 CellRangeAddress ranges = new CellRangeAddress(i+2,i+2, countCol,maxColspan);
					 sheet.addMergedRegion(ranges); 
				 }else if(!idSet.contains(ids[j])){
					 rowspan = assessPropManager.countRowspan(propList, ids[j]);
					 if(rowspan>1){
						 CellRangeAddress ranges = new CellRangeAddress(i+2,i+1+rowspan, countCol,countCol);
						 sheet.addMergedRegion(ranges); 
					 }
					 
				 }
			 }
			 cell_3.setCellValue(props[3]);
			 if(page.equals("result")){
				 String endId = ids[ids.length-1];
				 if(resultMap.containsKey(endId)){
					 AssessResult assessResult = resultMap.get(endId);
					 if(assessResult.getKpiValue()==null){
						 cell_2.setCellValue(""); 
					 }else{
						 cell_2.setCellValue(assessResult.getKpiValue()); 
					 }
					 if(assessResult.getScore()==null){
						 cell_5.setCellValue("");
					 }else{
						 cell_5.setCellValue(assessResult.getScore());
					 }
					 
					  
				 }
			 }else{
				 if(props[5].equals("2")){
					 cell_2.setCellValue("自动获取"); 
				 }
			 }
			 
			 
			 
//			 cell_4.setCellValue(props[4]);
//			 String endId = ids[ids.length-1];
//			 if(resultMap.containsKey(endId)){
//				 AssessResult assessResult = resultMap.get(endId);
//				 cell_2.setCellValue(assessResult.getKpiValue()); 
//				 cell_5.setCellValue(assessResult.getScore()); 
//			 }
			 
			 for(String id:ids){
				 idSet.add(id); 
			 }
			 
		}
	}
	
	
	
	
	
	

}
