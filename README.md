# readXml
read medical patent info from xml files and output the info to excel

xmlPojoList dimensions explained
               
             	[][0][][]: us patent application: 专利申请信息
             	[][1][][]: us bibliographic data application: 专利申请著录项目数据
             	[][2][][]: public reference: 公开信息
              [][3][][]: application reference: 申请信息
              [][4][][]: us application series code: 美国专利申请序列代码
              [][5][][]: priority claims: 优先权声明
              [][6 - 6+index][]: classifications ipcr: 国际专利分类数据 8 版
              [][7+index][][]:  invention title: 美国国家分类
              [][8+index][][]:  parties: 当事人信息：公司信息
              [][9+index][][]:  inventors: 当事人信息：发明者信息(assignees)
              [][11+index][][]: abstract: 摘要
              [][12+index+index2-1][][0-8]: drawings: 附图信息
              [][12+index+index2][][]: description: 附图说明
              [][14+index+index2][][0-index4]:  claims: 权利要求信息
              
              14 elements' name stored in [][xx][0][2]
              1st dimension represents different files 
              different element stored in 2nd dimension
              different attributes stored in 3rd dimension  
              different attributes' name stored in [][xx][x][0]
              different attributes' value stored in [][xx][x][1]
