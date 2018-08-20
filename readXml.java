
import java.io.*;
import java.util.ArrayList;  
import java.util.List;  
import java.util.Map;
import java.util.zip.ZipInputStream;
import java.util.HashMap;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.ParserConfigurationException;  
import org.w3c.dom.Document;  
import org.w3c.dom.Element;  
import org.w3c.dom.Node;  
import org.w3c.dom.NodeList;  
import org.xml.sax.SAXException;  
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class readXml 
{  
		private static final int BUFFER_SIZE = 4096;
		static int index = 0;		//counter for the number of classifications-ipcr
		static int index2 = 0;
		static int z = 0;			//counter for files
        public static void main(String[] args) throws FileNotFoundException, IOException
        {  
        	/*
             *  xmlPojoList dimensions explained
             *  
             *	[][0][][]: us patent application: 专利申请信息
             *	[][1][][]: us bibliographic data application: 专利申请著录项目数据
             *	[][2][][]: public reference: 公开信息
             *  [][3][][]: application reference: 申请信息
             *  [][4][][]: us application series code: 美国专利申请序列代码
             *  [][5][][]: priority claims: 优先权声明
             *  [][6 - 6+index][]: classifications ipcr: 国际专利分类数据 8 版
             *  [][7+index][][]:  invention title: 美国国家分类
             *  [][8+index][][]:  parties: 当事人信息：公司信息
             *  [][9+index][][]:  inventors: 当事人信息：发明者信息(assignees)
             *  [][11+index][][]: abstract: 摘要
             *  [][12+index+index2-1][][0-8]: drawings: 附图信息
             *  [][12+index+index2][][]: description: 附图说明
             *  [][14+index+index2][][0-index4]:  claims: 权利要求信息
             *  
             *  14 elements' name stored in [][xx][0][2]
             *  1st dimension represents different files 
             *  different element stored in 2nd dimension
             *  different attributes stored in 3rd dimension  
             *  different attributes' name stored in [][xx][x][0]
             *  different attributes' value stored in [][xx][x][1]
             *  
             *  ---------------------------------------------------------------------------------
             *  
             *  
             */
        	
        	//Start reading
        	String [][][][] XmlPojoList = new DomTest().parseXmlPojo();  
            
        	//Start Writing
           System.out.println("Before writing:");
           new DomTest().writeExcel(XmlPojoList);
           System.out.println("Finished writing.");
        }  
        private static String [][][][] readUsPatentApplication(String [][][][] xmlPojoList, Document document, File ifile)
        {

            NodeList list0 = document.getElementsByTagName("us-patent-application");
            Element e0 = (Element)list0.item(0);
            //xmlPojoList[z][0][0][29] = e0.
            xmlPojoList[z][0][0][0] = "us patent application:";
            String language0 = e0.getAttribute("lang");
            xmlPojoList[z][0][1][0] = "lang";
            xmlPojoList[z][0][1][1] = language0;
            String dtd_version0 = e0.getAttribute("dtd-version");
            xmlPojoList[z][0][2][0] = "dtd-version";
            xmlPojoList[z][0][2][1] = dtd_version0;
            String file0 = e0.getAttribute("file");
            xmlPojoList[z][0][3][0] = "file";
            xmlPojoList[z][0][3][1] = file0;
            String status0 = e0.getAttribute("status");
            xmlPojoList[z][0][4][0] = "status";
            xmlPojoList[z][0][4][1] = status0;
            String id0 = e0.getAttribute("id");
            xmlPojoList[z][0][5][0] = "id";
            xmlPojoList[z][0][5][1] = id0;
            String country0 = e0.getAttribute("country");
            xmlPojoList[z][0][6][0] = "country";
            xmlPojoList[z][0][6][1] = country0;
            String dateProduced0 = e0.getAttribute("date-produced");
            xmlPojoList[z][0][7][0] = "date-produced";
            xmlPojoList[z][0][7][1] = dateProduced0;
            String datePublished0 = e0.getAttribute("date-publ");
            xmlPojoList[z][0][8][0] = "date-published";
            xmlPojoList[z][0][8][1] = datePublished0;
        	return xmlPojoList;
        }
        private static String [][][][] readUsBibliographicDataApplication(String [][][][] xmlPojoList, Document document, File iflie)
        {
        	NodeList list1 = document.getElementsByTagName("us-bibliographic-data-application");
            //System.out.println(list0.getLength()+"!!!%%%");
            xmlPojoList[z][1][0][0] = "us-bibliographic-data-application:";
            
            Element e1 = (Element)list1.item(0);
            String language1 = e1.getAttribute("lang");
            xmlPojoList[z][1][1][0] = "lang";
            xmlPojoList[z][1][1][1] = language1;
            String country1 = e1.getAttribute("country");
            xmlPojoList[z][1][2][0] = "country";
            xmlPojoList[z][1][2][1] = country1;
        	return xmlPojoList;
        }
        private static String [][][][] readPublicReference(String [][][][] xmlPojoList, Document document, File iflie)
        {

            NodeList list2 = document.getElementsByTagName("publication-reference");
            xmlPojoList[z][2][0][0] = "publication-reference:";
            Element e2 = (Element)list2.item(0);
            String country2 = e2.getElementsByTagName("country").item(0).getFirstChild().getNodeValue();
            xmlPojoList[z][2][1][0] ="country";
            xmlPojoList[z][2][1][1] = country2;
            String docNum2 = e2.getElementsByTagName("doc-number").item(0).getFirstChild().getNodeValue();
            xmlPojoList[z][2][2][0]="doc-number";
            xmlPojoList[z][2][2][1] = docNum2;
            String kind2 = e2.getElementsByTagName("kind").item(0).getFirstChild().getNodeValue();
            xmlPojoList[z][2][3][0] = "kind";
            xmlPojoList[z][2][3][1] = kind2;
            String date2 = e2.getElementsByTagName("date").item(0).getFirstChild().getNodeValue();
            xmlPojoList[z][2][4][0] = "date";
            xmlPojoList[z][2][4][1] = date2;
        	return xmlPojoList;
        }
        private static String [][][][] readApplicationReference(String [][][][]xmlPojoList, Document document, File iflie)
        {

            NodeList list3 = document.getElementsByTagName("application-reference");
            xmlPojoList[z][3][0][0] = "application-reference:";
            Element e3 = (Element)list3.item(0);
            String applyType = e3.getAttribute("appl-type");
            xmlPojoList[z][3][1][0] = "appl-type";
            xmlPojoList[z][3][1][1] = applyType;
            //System.out.println(xmlPojoList[3][0]);
           if(e3!=null)
           {
        	   //System.out.println("Test!");
        	   
        	   
        	   String country3 = e3.getElementsByTagName("country").item(0).getFirstChild().getNodeValue();
        	   xmlPojoList[z][3][2][0] = "country";
        	   xmlPojoList[z][3][2][1] = country3;
               String docNum3 = e3.getElementsByTagName("doc-number").item(0).getFirstChild().getNodeValue();
               xmlPojoList[z][3][3][0] = "doc-number";
               xmlPojoList[z][3][3][1] = docNum3;
               String date3 = e3.getElementsByTagName("date").item(0).getFirstChild().getNodeValue();
               xmlPojoList[z][3][4][0] = "date";
               xmlPojoList[z][3][4][1] = date3;
            
           }
        	return xmlPojoList;
        }
        private static String [][][][] readUsApplicationSeriesCode(String [][][][] xmlPojoList, Document document, File iflie)
        {

            NodeList list4 = document.getElementsByTagName("us-application-series-code");
            xmlPojoList[z][4][0][0] = "us-application-series-code:";
            String sCode4 = list4.item(0).getFirstChild().getNodeValue();
            xmlPojoList[z][4][1][0] = "us-application-series-code";
            xmlPojoList[z][4][1][1] = sCode4;
            
        	return xmlPojoList;
        }
        private static String [][][][] readPriorityClaims(String [][][][] xmlPojoList, Document document, File iflie)
        {
            
            NodeList list5 = document.getElementsByTagName("priority-claims");
            
            xmlPojoList[z][5][0][0] = "priority-claims:";
            if(list5.getLength()==0)
            {
            	return xmlPojoList;
            }
             Element e5 = (Element)list5.item(0);
             
             NodeList list5_1 = e5.getElementsByTagName("priority-claim");
             Element e5_1 = (Element)list5_1.item(0);
             String sequence5 = e5_1.getAttribute("sequence");
             xmlPojoList[z][5][1][0] = "sequence";
             xmlPojoList[z][5][1][1] = sequence5;
             
             String kind5 = e5_1.getAttribute("kind");
             xmlPojoList[z][5][2][0] = "kind";
             xmlPojoList[z][5][2][1] = kind5;
             //System.out.println(sequence5+"!!!"+kind5);
             
             String applyType5 = e5_1.getElementsByTagName("country").item(0).getFirstChild().getNodeValue();
             xmlPojoList[z][5][3][0] = "country";
             xmlPojoList[z][5][3][1] = applyType5;
             //System.out.println(xmlPojoList[5][0]);
            if(e5!=null)
            {
         	   //System.out.println("Test!");
         	   
         	   /*
         	   String country5 = e5.getElementsByTagName("country").item(0).getFirstChild().getNodeValue();
                xmlPojoList[5][3] = country5;*/
                String docNum5 = e5.getElementsByTagName("doc-number").item(0).getFirstChild().getNodeValue();
                xmlPojoList[z][5][4][0] = "doc-number";
                xmlPojoList[z][5][4][1] = docNum5;
                String date5 = e5.getElementsByTagName("date").item(0).getFirstChild().getNodeValue();
                xmlPojoList[z][5][5][0] = "date";
                xmlPojoList[z][5][5][1] = date5;
             
            }
            
        	return xmlPojoList;
        }
        private static String [][][][] readClassificationsIpcr(String [][][][] xmlPojoList, Document document, File iflie)
        {

            NodeList list6 = ((Element)document.getDocumentElement()).getElementsByTagName("classifications-ipcr");
            xmlPojoList[z][6][0][0] = "classification-ipcr:";
           
            int count = list6.getLength();
            index = count;
            Element [] e6 = new Element[count];
            for(int j=0; j<count; j++)
            {
            Element e6i = (Element)list6.item(j);
            
            
            //取二级元素indicator 
            Element message = document.getDocumentElement();
            NodeList indicatorList6 = message.getElementsByTagName("ipc-version-indicator");
            Element indicator6 = (Element) indicatorList6.item(0);
            xmlPojoList[z][j+6][1][0] = "ipc-version-indicator";
            //取三级元素date
            NodeList dateList6 = indicator6.getElementsByTagName("date");
            
            xmlPojoList[z][j+6][2][0] = "date";
            Element e6_0 = (Element)dateList6.item(0);
            String date6 = e6_0.getFirstChild().getNodeValue();
            xmlPojoList[z][j+6][2][1] = date6;
            
            String cLevel6 = e6i.getElementsByTagName("classification-level").item(0).getFirstChild().getNodeValue();
            xmlPojoList[z][j+6][3][0] = "classification-level";
            xmlPojoList[z][j+6][3][1] = cLevel6;
            //System.out.println(cLevel6+"{{{{{{");
            String section6 = e6i.getElementsByTagName("section").item(0).getFirstChild().getNodeValue();
            xmlPojoList[z][j+6][4][0] = "section";
            xmlPojoList[z][j+6][4][1] = section6;
            String class6 = e6i.getElementsByTagName("class").item(0).getFirstChild().getNodeValue();
            xmlPojoList[z][j+6][5][0] = "class";
            xmlPojoList[z][j+6][5][1] = class6;
            String subclass6 = e6i.getElementsByTagName("subclass").item(0).getFirstChild().getNodeValue();
            xmlPojoList[z][j+6][6][0] = "subclass";
            xmlPojoList[z][j+6][6][1] = subclass6;
            String mainGroup6 = e6i.getElementsByTagName("main-group").item(0).getFirstChild().getNodeValue();
            xmlPojoList[z][j+6][7][0] = "main-group";
            xmlPojoList[z][j+6][7][1] = mainGroup6;
            String subGroup6 = e6i.getElementsByTagName("subgroup").item(0).getFirstChild().getNodeValue();
            xmlPojoList[z][j+6][8][0] = "subgroup";
            xmlPojoList[z][j+6][8][1] = subGroup6;
            String symbolPosition6 = e6i.getElementsByTagName("symbol-position").item(0).getFirstChild().getNodeValue();
            xmlPojoList[z][j+6][9][0] = "symbol-position";
            xmlPojoList[z][j+6][9][1]= symbolPosition6;
            String classificationValue6 = e6i.getElementsByTagName("classification-value").item(0).getFirstChild().getNodeValue();
            xmlPojoList[z][j+6][10][0] = "classification-value";
            xmlPojoList[z][j+6][10][1] = classificationValue6;
            
            NodeList actionDate6_1 = e6i.getElementsByTagName("action-date");
            xmlPojoList[z][j+6][11][0] = "action-date";
            Element e_actionDate6_1 = (Element)actionDate6_1.item(0);
            String actionDate6 = e_actionDate6_1.getElementsByTagName("date").item(0).getFirstChild().getNodeValue();
            xmlPojoList[z][j+6][11][1] = "date";
            xmlPojoList[z][j+6][11][2] = actionDate6;
            
            NodeList gCountry6_1 = e6i.getElementsByTagName("generating-office");
            Element e_gCountry6_1 = (Element)gCountry6_1.item(0);
            xmlPojoList[z][j+6][12][0] = "generating-office";
            String gCountry6 = e_gCountry6_1.getElementsByTagName("country").item(0).getFirstChild().getNodeValue();
            xmlPojoList[z][j+6][12][1] = "country";
            xmlPojoList[z][j+6][12][2] = gCountry6;
            
            String cStatus6 = e6i.getElementsByTagName("classification-status").item(0).getFirstChild().getNodeValue();
            xmlPojoList[z][j+6][13][0] = "classification-status";
            xmlPojoList[z][j+6][13][1] = cStatus6;
            String cDateSource6 = e6i.getElementsByTagName("classification-data-source").item(0).getFirstChild().getNodeValue();
            //System.out.println(cDateSource6+"!!!!!!");
            xmlPojoList[z][j+6][14][0] = "classification-data-source";
            xmlPojoList[z][j+6][14][1] = cDateSource6;
            }
            /*
            for(int i = 0; i < xmlPojoList[6].length;i++)
            {
            	if(xmlPojoList[j+5][i] != null)
            	{	
            		System.out.println(xmlPojoList[j+5][i]+"))))");
            	}
            }
            */
            //System.out.println(node4.getTextContent()/*+"!!5!!!"*/);
        	return xmlPojoList;
        }
        private static String [][][][] readinventionTitle(String [][][][] xmlPojoList, Document document, File iflie)
        {

            NodeList list8 = document.getElementsByTagName("invention-title");
            //System.out.println(list8.getLength()+"%%%");
            Element [] e = new Element[list8.getLength()];
            e[0] = (Element)list8.item(0);
            xmlPojoList[z][7+index][0][0] = "invention-title:";
            /*String id = e[0].getAttribute("id");
            xmlPojoList[7+index][0] = id;
            //System.out.println(id);
*/                String id8 = e[0].getAttribute("id");
			xmlPojoList[z][7+index][1][0] = "id";
			xmlPojoList[z][7+index][1][1] = id8;
            //String iTitle = e8.getElementsByTagName("invention-title").item(0).getFirstChild();
            //
            String iTitle = list8.item(0).getFirstChild().getNodeValue();
            xmlPojoList[z][7+index][2][0] = "Title";
            xmlPojoList[z][7+index][2][1] = iTitle;
            
        	return xmlPojoList;
        }
        private static String [][][][] readParties(String [][][][] xmlPojoList, Document document, File iflie)
        {

            NodeList list10 = document.getElementsByTagName("us-parties");
            //System.out.println(list10+"sgfxjfhkc");
            Node node10 = list10.item(0);
            xmlPojoList[z][8+index][0][0] = "us-parties:";
            //System.out.println(node10);
            //System.out.println(index+"#####");
           
            Element e10 = (Element)list10.item(0);
            
            NodeList list10_1 = document.getElementsByTagName("us-applicant");
            Element e10_1 = (Element)list10_1.item(0);
            xmlPojoList[z][8+index][1][0] = "us-applicant";
            String sequence = e10_1.getAttribute("sequence");
            xmlPojoList[z][8+index][2][0] = "sequence";
            xmlPojoList[z][8+index][2][1] = sequence;
            //System.out.println(sequence+"-----");
            String appType = e10_1.getAttribute("app-type");
            xmlPojoList[z][8+index][3][0] = "app-type";
            xmlPojoList[z][8+index][3][1] = appType;
            String designation = e10_1.getAttribute("designation");
            xmlPojoList[z][8+index][4][0] = "designation";
            xmlPojoList[z][8+index][4][1] = designation;
            String aac = e10_1.getAttribute("applicant-authority-category");
            xmlPojoList[z][8+index][5][0] = "applicant-authority-category";
            xmlPojoList[z][8+index][5][1] = aac;
             
            if(node10 != null)
            {
            	//address book
            	if( e10.getElementsByTagName("orgname").item(0)!= null)
            	{
            		String orgName10 = e10.getElementsByTagName("orgname").item(0).getFirstChild().getNodeValue();
            		xmlPojoList[z][8+index][6][0] = "orgname";
            		xmlPojoList[z][8+index][6][1] = orgName10;
            	}
            	else
            	{
            		String lastname = e10.getElementsByTagName("last-name").item(0).getFirstChild().getNodeValue();
            		String firstname = e10.getElementsByTagName("first-name").item(0).getFirstChild().getNodeValue();
            		String name = lastname + " " + firstname;
            		xmlPojoList[z][8+index][6][0] = "applicant name";
            		xmlPojoList[z][8+index][6][1] = name;
            	}
                String city10 = e10.getElementsByTagName("city").item(0).getFirstChild().getNodeValue();
                xmlPojoList[z][8+index][7][0] = "city";
                xmlPojoList[z][8+index][7][1] = city10;
                String country10 = e10.getElementsByTagName("country").item(0).getFirstChild().getNodeValue();
                xmlPojoList[z][8+index][8][0] = "country";
                xmlPojoList[z][8+index][8][1] = country10;
            	
                //residence
                String country10_1 = e10.getElementsByTagName("country").item(0).getFirstChild().getNodeValue();
                xmlPojoList[z][8+index][9][0] = "residence country";
                xmlPojoList[z][8+index][9][1] = country10_1;
                
                
                
            }
            
           //inventors / assignees
           
            NodeList list10_2 = document.getElementsByTagName("inventor");
            Element e10_2 = (Element)list10_2.item(0);
            xmlPojoList[z][9+index][0][0] = "inventor";
            String sequence2 = e10_2.getAttribute("sequence");
            xmlPojoList[z][9+index][1][0] = "sequence";
            xmlPojoList[z][9+index][1][1] = sequence2;
            
            String designation1 = e10_2.getAttribute("designation");
            //System.out.println(designation1+"-----");
            xmlPojoList[z][9+index][2][0] = "designation";
            xmlPojoList[z][9+index][2][1] = designation1;
            
             
            if(node10 != null)
            {
            	//address book
            	String lastName10 = e10.getElementsByTagName("last-name").item(0).getFirstChild().getNodeValue();
            	xmlPojoList[z][9+index][3][0] = "last-name";
            	xmlPojoList[z][9+index][3][1] = lastName10;
                String firstName10 = e10.getElementsByTagName("first-name").item(0).getFirstChild().getNodeValue();
                xmlPojoList[z][9+index][4][0] = "first-name";
                xmlPojoList[z][9+index][4][1] = firstName10;
                String city10 = e10.getElementsByTagName("city").item(0).getFirstChild().getNodeValue();
                xmlPojoList[z][9+index][5][0] = "city";
                xmlPojoList[z][9+index][5][1] = city10;
                String country10 = e10.getElementsByTagName("country").item(0).getFirstChild().getNodeValue();
                xmlPojoList[z][9+index][6][0] = "country";
                xmlPojoList[z][9+index][6][1] = country10;
               
                
            }
            
            
        	return xmlPojoList;
        }
        private static String [][][][] readAbstract(String [][][][] xmlPojoList, Document document, File iflie)
        {

            NodeList list12 = document.getElementsByTagName("p");
            
            xmlPojoList[z][11+index][0][0] = "abstract:";
            Element e12 = (Element)list12.item(0);
            String id12 = e12.getAttribute("id");
            //System.out.println(id12);
            xmlPojoList[z][11+index][1][0] = "id";
            xmlPojoList[z][11+index][1][1] = id12;
            String num12 = e12.getAttribute("num");
            //System.out.println(num12);
            xmlPojoList[z][11+index][2][0] = "num";
            xmlPojoList[z][11+index][2][1] = num12;
            
            //String text1 = e12.getElementsByTagName("p").item(0).getFirstChild().getNodeValue();
            
            String text12 = list12.item(0).getTextContent();
            
            xmlPojoList[z][11+index][3][0] = " ";
            xmlPojoList[z][11+index][3][1] = text12;
            
        	return xmlPojoList;
        }
        private static String [][][][] readDrawings(String [][][][] xmlPojoList, Document document, File iflie)
        {

            
            NodeList listn = document.getElementsByTagName("figure");
            for(int h = 0; h < listn.getLength(); h++)
            {
            	xmlPojoList[z][12+index+h][1][0] = "figure: ";
            }
            
           //System.out.println(listn.getLength()+"~~~~");
            
            
            for(int j = 0; j < listn.getLength(); j++)
            {
            	
            	Element e13 = (Element)listn.item(j);
            	////取二级元素figure
            	
            	String id13 = e13.getAttribute("id");
            	xmlPojoList[z][12+index+index2][1][0] = "id";
            	xmlPojoList[z][12+index+index2][1][1] = id13;
            	//System.out.println(id13+"NNNNN");
            	String num13 = e13.getAttribute("num");
            	xmlPojoList[z][12+index+index2][2][0] = "num";
            	xmlPojoList[z][12+index+index2][2][1] = num13;
            	//System.out.println(index+" "+index2+"3457345678346");
            	
            	//取三级元素img 
                Element message = document.getDocumentElement();
                NodeList indicatorList13 = message.getElementsByTagName("drawings");
                Element e13_1 = (Element) indicatorList13.item(0);
                xmlPojoList[z][12+index+index2][0][0] = "drawings";
                
                NodeList dateList6 = e13_1.getElementsByTagName("img");
                xmlPojoList[z][12+index+index2][3][0] = "img";
                Element e13_2 = (Element)dateList6.item(j);
                String id13_1 = e13_2.getAttribute("id");
                xmlPojoList[z][12+index+index2][4][0] = "id";
                xmlPojoList[z][12+index+index2][4][1] = id13_1;
                String he13 = e13_2.getAttribute("he");
                xmlPojoList[z][12+index+index2][5][0] = "height";
                xmlPojoList[z][12+index+index2][5][1] = he13;
                String wi13 = e13_2.getAttribute("wi");
                xmlPojoList[z][12+index+index2][6][0] = "width";
                xmlPojoList[z][12+index+index2][6][1] = wi13;
                String file13 = e13_2.getAttribute("file");
                xmlPojoList[z][12+index+index2][7][0] = "file";
                xmlPojoList[z][12+index+index2][7][1] = file13;
                String alt13 = e13_2.getAttribute("alt");
                xmlPojoList[z][12+index+index2][8][0] = "alt";
                xmlPojoList[z][12+index+index2][8][1] = alt13;
                String iContent13 = e13_2.getAttribute("img-content");
                xmlPojoList[z][12+index+index2][9][0] = "img-content";
                xmlPojoList[z][12+index+index2][9][1] = iContent13;
                String iFormat13 = e13_2.getAttribute("img-format");
                xmlPojoList[z][12+index+index2][10][0] = "ing-format";
                xmlPojoList[z][12+index+index2][10][1] = iFormat13;
                /*
            	for(int l=0;l<xmlPojoList[12+index+index2].length;l++)
            	{
            		if(xmlPojoList[12+index+index2][l]!=null)
            		{
            			System.out.println(xmlPojoList[12+index+index2][l]+"'''''''''");
            		}
            	}
            	*/
            	index2++;
            }
        	return xmlPojoList;
        }
        private static String [][][][] readDescription(String [][][][] xmlPojoList, Document document, File iflie)
        {

          //  int index3 =0;	//count the number of descriptions
            
            //get the whole description
            NodeList deList = document.getElementsByTagName("description");
            xmlPojoList[z][12+index+index2][0][0]="description:";
           
            Element e14_1 = (Element)deList.item(0);
            
            //get all headings and p_s
            NodeList listAll = e14_1.getElementsByTagName("*");									//Headings and ps
            int h = 0; 	//heading number	
            int p = -1;		//p number
            //clear all the nulls
            for(int s = 0; s < xmlPojoList[12+index+index2].length; s++)
            {
            	xmlPojoList[z][12+index+index2][s][1] = "";
            }
            for(int o = 0; o < listAll.getLength(); o++)
            {
            	Node temp = listAll.item(o);
            	
            	switch (temp.getNodeName())
            	{
            		case "heading":
            			h++;		//next heading met
            			p++;		//give space for the heading's description
            			
            			//eliminate white spaces in the heading
            			String temp1 = temp.getTextContent().trim();
            			while(temp1.startsWith("　"))
            			{
            				temp1 = temp1.substring(1,temp1.length()).trim();
            			}
            			while(temp1.endsWith("　"))
            			{
            				temp1 = temp1.substring(0,temp1.length()-1).trim();
            			}
            				//Store the heading
            				xmlPojoList[z][12+index+index2][h][0] = temp1+": ";
            			
            			break;
            		case "p":
            			//eliminate all white spaces in temp
            			String temp2 = temp.getTextContent().trim();
            			while(temp2.startsWith("null"))
            			{
            				temp2 = temp2.substring(1,temp2.length()).trim();
            			}
            			while(temp2.endsWith(" "))
            			{
            				temp2 = temp2.substring(0,temp2.length()-1).trim();
            			}
            			//add it under the heading
            			xmlPojoList[z][12+index+index2][h][1] += temp2;
            			
            			break;
            		/*case "figref":
            			break;
            		case "b":
            			break;
            			*/
            	}
            }
        	return xmlPojoList;
        }
        private static String [][][][] readClaims(String [][][][] xmlPojoList, Document document, File iflie)
        {

            NodeList list15 = document.getElementsByTagName("claim");
            xmlPojoList[z][13+index+index2][0][0] = "claim: ";
            //System.out.println(list15.getLength()+"PPPPPPPPPPPp");
            int index4 = 0;
            int l = 0;
            String temp = null;
            String cid=" ";
            while(l < list15.getLength())
            {
            //System.out.println(l);
          	  Element e15 = (Element)list15.item(l);
          	  //String actionDate6 = e15.getElementsByTagName("date").item(0).getFirstChild().getNodeValue();
                int c = 0;	//counter for the claim-text loop
                
          	  while(((e15.getElementsByTagName("claim-text").item(c))!=null ))
          	  {
          		  
          		  //System.out.println("@@@@"+e15.getElementsByTagName("claim-text").item(c).getTextContent());
          		  cid = e15.getAttribute("id");
          		  //test.indexOf("This")!=-1
          		  if(e15.getElementsByTagName("claim-text").item(c).getFirstChild().getNodeValue() != null && e15.getElementsByTagName("claim-text").item(c).getTextContent().trim() != null)
          		  {
          			  temp += e15.getElementsByTagName("claim-text").item(c).getTextContent();
          			  xmlPojoList[z][14+index+index2][index4][1]=temp;
          		  }
          		  
          		  //System.out.println("@@@@"+j+"@@@@");
          		 // System.out.println("~~"+l+"~~~"+temp);
          		  
          		  
          		  
          		  
          		  /*
          		   * 
          		   * 
          		   * 
          		   * 写到这了！！！！！！！
          		   * 
          		   * 
          		   * 
          		   * 
          		   * 
          		   * 
          		   * 
          		   * 
          		   * */
          		  //System.out.println(")))))))"+xmlPojoList[14+index+index2][index4]);
          		  
          		  
          		  /*
          		  //Test
          		  for(int u = 0; u < xmlPojoList[14+index+index2].length; u++)
          		  {
          			  if(xmlPojoList[14+index+index2][u]!=null)
          			  {
          				 // System.out.println(")))))))"+xmlPojoList[14+index+index2][u]);
          			  }	
          		  }
          		  */
          		  // xmlPojoList[14+index][0] = temp;
          		  c++;
          		  
          	  }
          	  //System.out.println(temp);
          		  
          	  if(temp!=null && temp.trim()!="")
          	  {
          		  //System.out.println("Claim:"+temp);
          		  
          		  xmlPojoList[z][13+index+index2][0][1]=temp;
          	  }
          	index4++;
          	  temp = "";
          	 l++;
            }
           z++;
          // System.out.println(z);
        	return xmlPojoList;
        }
       //read xml file，store information of patents into 4 dimension String array and return it
        private static String [][][][] parseXmlPojo() 
        {  
        	String [][][][] xmlPojoList = new String[30][30][30][30];  
            try {  
            	int n = 2;	//number of files needed to be read
            	File [] ifile = new File[n];
            	
            	//assign files to ifile[]
            	ifile[0] = new File("US20180002001A1-20180104.XML");
            	ifile[1] = new File("US20180002000A1-20180104.XML");
                
            	//loop the reading procedure for each ifile[t]
            	for (int t = 0; t < n; t++)
                {
            	 
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
                DocumentBuilder builder = factory.newDocumentBuilder();  
                Document document = builder.parse(ifile[t]); 
                
                
                //start reading xml
                    
                //reading <us-patent-application>
                xmlPojoList = DomTest.readUsPatentApplication(xmlPojoList, document, ifile[t]);
               
                //reading <us-bibliographic-data-application>
                xmlPojoList = DomTest.readUsBibliographicDataApplication(xmlPojoList, document, ifile[t]);
          
                //reading <publication-reference>
                xmlPojoList = DomTest.readPublicReference(xmlPojoList, document, ifile[t]);
               
                //reading <application-reference>
                xmlPojoList = DomTest.readApplicationReference(xmlPojoList, document, ifile[t]);
               
               //reading <us-application-series-code>
               xmlPojoList = DomTest.readUsApplicationSeriesCode(xmlPojoList, document, ifile[t]);
                
               //reading <priority-claims>
                 xmlPojoList = DomTest.readPriorityClaims(xmlPojoList, document, ifile[t]);
              
              //reading <classifications-ipcr>
              xmlPojoList = DomTest.readClassificationsIpcr(xmlPojoList, document, ifile[t]);
              
              /*  
              //reading <classifications-national>
                xmlPojoList = DomTest.readUsPatentApplication(xmlPojoList, document, ifile[t]);
                
                NodeList list7 = document.getElementsByTagName("classifications-national");
                Node node7 = list7.item(0);
                
                if(node7 != null)
                {
                	xmlPojoList[z][6+index][0][0] = "classifications-national";
                	xmlPojoList[z][6+index][0][1] = node7.getTextContent();
               }
               
               //if needed
               
               */ 
                
              	//reading <invention-title>
                xmlPojoList = DomTest.readinventionTitle(xmlPojoList, document, ifile[t]);
                
                //reading <us-parties>
                xmlPojoList = DomTest.readParties(xmlPojoList, document, ifile[t]);
                
                //reading <abstract>
                xmlPojoList = DomTest.readAbstract(xmlPojoList, document, ifile[t]);
              
                //reading <drawings>
                xmlPojoList = DomTest.readDrawings(xmlPojoList, document, ifile[t]);
          	
                //reading <description>
                xmlPojoList = DomTest.readDescription(xmlPojoList, document, ifile[t]);
               
                //reading <claims>
                xmlPojoList = DomTest.readClaims(xmlPojoList, document, ifile[t]); 
             
                }//end of for
            } catch (ParserConfigurationException e) 
            {  
                e.printStackTrace();  
            } catch (SAXException e) 
            {  
                e.printStackTrace();  
            } catch (IOException e) 
            {  
                e.printStackTrace();  
            } 
            
            return xmlPojoList;  
        } 
        private static void writeExcel(String [][][][] XmlPojoList)
        {
        	//Writing Excel
            //Initialize workbook & sheet
            
            HSSFWorkbook workBook = new HSSFWorkbook();
            HSSFSheet sheet1 = workBook.createSheet("Patent Summary");
            
            FileOutputStream out = null;
            int x = 0;
            
            //calculate the number of rows required
            //find the largest number of attributes among the elements
            int longestInsideArray = 0;
            int longestMiddleArray = 0;
            for(int r = 0; r < XmlPojoList.length; r++)
            {
            	for (int q = 0; q < XmlPojoList.length; q++) 
            	{  
            		if( longestMiddleArray < XmlPojoList[r][q].length)
            		{
            			longestMiddleArray = XmlPojoList[r][q].length;
        			
            		}
            		for(int w = 0; w < XmlPojoList[r][q].length; w++)
            		{	
            			if( longestInsideArray < XmlPojoList[r][q][w].length)
            			{
            				longestInsideArray = XmlPojoList[r][q][w].length;
            				
            			}
            		}
            }
            } 
            int rowsNeeded = XmlPojoList.length * longestMiddleArray * longestInsideArray;	
           //System.out.println(rowsNeeded);
            
            
            //write to the sheet
            try 
            {
            	HSSFRow [] row = new HSSFRow[27000];		//rowsNeeded+XmlPojoList.length  :   extra space for blank lines between categories
            	
            	//creating needed rows 
            	for(int v = 0; v< rowsNeeded; v++)
            	{
            		row[v] = workBook.getSheet("Patent Summary").createRow(v);
            	}
            	System.out.println("Start writing");
            	
            	//write information to cells
            	
            	//Name of the files should be obtained from  extracting the zip, here it is set to the test file
            	HSSFCell cell0 = row[0].createCell(0);
            	cell0.setCellValue("US2018002000A1-20180104.xls");
            	HSSFCell cell1 = row[3].createCell(0);
            	cell1.setCellValue("US2018000002A1-20180104.xls");
            	
            	
            	
            	int c = 1;		//counter for the columns
            	
            	for (int r = 0; r < XmlPojoList.length; r++)
            	{
            		/*for(int g = 0; g < 2; g++)			//print out the name of files, g is temp value, will be changed after zip method added
            		{									//temporarily it is achieved at line 160
            			
            		}*/
            		for (int q = 0; q < XmlPojoList[r].length; q++) 
            		{  
            				for(int w = 0; w < XmlPojoList[r][q].length;w++)
            				{	
            						for(int e = 0; e < XmlPojoList[r][q][w].length; e++)
            						{
            							if(XmlPojoList[r][q][w][e] != null)
            							{
            								
            								HSSFCell cell = row[e+3*r].createCell(c);  			//new file starts 3 (2+1) rows after the first line of last file
            								cell.setCellValue(XmlPojoList[r][q][w][e]);
            								//System.out.println(q+"!"+w+"!"+e+"!"+XmlPojoList[q][w][e]);
            								
            							}
                					
            						}
            						if(XmlPojoList[r][q][w][0] != null)
            						{
            							//System.out.println("!"+w+"!"+XmlPojoList[q][w][0]);
            							c++;
            						}
                			
            						//System.out.println(c+"!");
            						
            				}
            				
            				//System.out.println(c+"!!");
            				if(XmlPojoList[r][q][0][0] != null)
            				{
            					//System.out.println("!"+q+"!"+XmlPojoList[q][0][0]);
            					c++;
            				}
            		}
            		c = 1;
            		}
            	
            	
            	out = new FileOutputStream("patentInfo.xls");
            	workBook.write(out);
            	
            	
            }
            catch(Exception e)
            {
            	e.printStackTrace();
            }
           
        }
        
        
        
        
        /*
        //Unzip		//Not Done Yet
        private void extractFile(String originPath, String destFilePath) throws FileNotFoundException
        {
        	ZipInputStream zipIn = new ZipInputStream(new FileInputStream(originPath));
    		try{
    			//if file exists, replace it. 
    			//if file does NOT exist, copy it.
    			File destFile = new File(destFilePath);
    			if(!destFile.exists())
    			{
    				destFile.getParentFile().mkdirs();
    			}

    			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));
    			byte[] bytesIn = new byte[BUFFER_SIZE];
    			int read = 0;
    			while ((read = zipIn.read(bytesIn)) != -1) {
    				bos.write(bytesIn, 0, read);
    			}  
    			bos.close();   
    		}
    		catch(Exception e){
    			System.out.println(e.getMessage());
    			System.exit(0);
    		}
    	}*/
    }