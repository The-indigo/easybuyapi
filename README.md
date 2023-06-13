# easybuyapi

<H5>Prerequisites</H5>
<ul>
  <li>JDK 17</li>
  <li>Maven</li>
  <li>SQL</li>
</ul>

<h4>Technologies</h4>
<ul>
<li>Springboot</li>
  <li>Spring security</li>
  <li>Spring data jpa</li>
  <li>MYSQL</li>
</ul>

<h4>Running with docker</h4>

<p>In your docker engine, pull the images from docker hub "theyemi/easybuyapi" and "theyemi/easybuydb"</p>
<p>Run both images in the same network, use "easybuydb as the container name when running the database image. 
  i.e docker run -d --name easybuydb --net easybuy-net -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes theyemi/easybuydb
      docker run -d --net easybuy-net -p 8080:8080 --name easybuyapi theyemi/easybuyapi
  This is to ensure that the api uses 
  the database name to map to the database ip address as seen in the database url property value in the 
  <i>application.properties file<i>
  spring.datasource.url=jdbc:mysql://easybuydb:3306/easybuy
    </p>
    
  <h3>To test with an already registered user account Enter the url in this format </h3>
    
     
     http://<youripaddress>:8080/easybuyapi/v1/login
  using a post request
    {
          "email":"ade@gmail.com", 
         "password":"adeyemi"
    }      

  <h3>To register a user </h3>
    
    http://<youripaddress>:8080/easybuyapi/v1/register
  using a post request
{
    "email":string, 
    "password":string, 
    "fullname":string, 
    "phonenumber":string,
    "address":string
}    
      
      
