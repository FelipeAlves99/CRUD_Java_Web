

# Making a simple CRUD with JavaWeb (Unip EXAM)

In this tutorial, you will find a step-by-step on what you should do to make a simple **CRUD with JavaWeb**. Follow each step and in the end you will probably be ready to Unip's ALPOO Test.
The source code is open, but everything you need will be in the **README**.

## Preparing Ambient

First of all, you gonna need Netbeans and JDK installed in your machine. Links for both:
**[Netbeans Download](https://netbeans.org/downloads/8.0.2/)**

 - Download the biggest one, that has almost everything checked.

**[JDK Download](https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)**

 - Get the version that matches your computer config. I mean windows x64 or x86, Linux or MacOS. 

Also need some SQL shenanigans

[**SQL Server Express**](https://www.microsoft.com/pt-br/sql-server/sql-server-downloads)
 - A simple next-next-next installation
 - In the end click to install the SSMS. You are going to need it.

**[SQL Serve Driver for Java](https://docs.microsoft.com/pt-br/sql/connect/jdbc/microsoft-jdbc-driver-for-sql-server?view=sql-server-ver15)**

- You will need this one later in the tutorial

## Starting with Java Web

For now, let's forget about coding. I know, the test is all about coding as fast as possible, but first, you gonna need to configure some things to your project, else you will fail the test and go to **EXAME** and we don't want that. 

### The SQL Script

If you are doing this in your PC, probably the SQL Server Management Studio is not configured. Let's configure it:

 1. Configure the *sa* user and habilitate SQL Server login
 - Open SQL Server Management Studio and click in connect
 - Now, in the left window, expand Security > Logons, find *sa*, right click it and go to properties.
 - Change the password (do not forget)
 - Go to status and conced and habilitate both radio buttons.
 - After that, close the sa properties and right click the instance of SQL (the first item in the left menu)
 - Go to its properties and security. In the Autentication Server, select the second option.

 2. Open the door 1433 (Netbeans will need this open)
 - Open the SQL Server Configuration Manager
 - Expand the  SQL Server Network Configuration
 - Select the protocols (this will open some options in the right)
 - Habilitate the TCP/IP and right click it and open its properties
 - In the Ip Address tab, scroll down until you find IPAII
 - Put in the TCP Door: 1433
 - Reset the SQL Serve Express Services. Open the windows services and find it

3. Finally the script!

For this example, we will make a CRUD of a car. So create a new SQL query and put this script

    create database ALPOO
    go
    
    use ALPOO
    go
    
    create table Carros
    (
	    id int primary key identity (1,1),
	    fabricante varchar (50),
	    modelo varchar (50),
	    ano varchar (4),
	    valor money
    )
    go

That's it for SQL Server. In the test, you only need to do the part 3.

### Creating the Netbeans project

 1. Create a new project. Java Web > Web Application

 #### Hibernate configuration
 
 1.  Open the services tab (**ctrl+5** or **Windows > Services**)
 2. Right click the "Database" and new connection.
 3. Remember the SQL Driver? Find it and select in this new window (the name that will be displayed is **Microsoft SQL Server 2005**)
 4. Click next and put put this:
 - **Host:** localhost
 - **Door:** 1433
 - **Database:** ALPOO
 - **Instance:** It is the text when you click to connect the SQL Server, in my case is FELIPE-NOTEBOOK\SQLEXPRESS
 - **User:** sa
 - **Password:** sa's password
5. CLICK TEST CONNECTION. Sorry for the caps, but it is a **must do** thing.
6. Click next and select dbo.
7. Conclud the process. 

Now let's see if the Hibernate managed to find your connection

1. Go back to your project tab (ctrl+1)
2. Right click **Source Code Packages**
3. Go to 'others' and find the hibernate directory
4. Select the first one. Click in next and select the connection you had just created 
5. After clicking in 'finish', open the file and expand the first drop down menu. There should be the connection string configurations there. If it's not there, delete this project and start over the netbeans part (frustrating, I know).

Ok, our Hibernate is good to go let's do the next step

1. Create the package **Modelo** and inside it, create a new item
2. Click to add > others > persistence > Database entity class
3. Select your connection string and add your **Carros** table
4. Click next and change the class name to **Carro**
5. Add this line of code inside the model file, right below the `@Column(name = "id")`: `@GeneratedValue`
6. Click next one more time and in the collection type, select *java.util.list*
7. Finish
8. Now open again the hibernate config file and go to mapping
9. There you will add this class you just created, the **Carro**

Now it's the last step for hibernate, finally.
1. Right click your project/solution (in my case is WebEstudos) name in that left menu
2. Go to Build > Compilation and uncheck the option **"Activate Annotation Process"**
3. Click to build the solution. 

Now, you should be good to go with the project.

### Creating the DAL package

After all that hibernate thing, we can start coding! After one more part... It's a quick one, I swear.

1. As the title says, create the DAL package.
2. Let's create the class '*Conexao*'. Same process, Add > others > hibernate > hibernateUtil.java and name it as Conexao.

Congrats! You have probably 2/10 in the test. :(
BUT **now we can code!** 

Let's make the **CarroDAO** class

 1. It's a simple java class here, so just add one.
 
Insert this code inside of the class { }

    public String mensagem;
    Conexao conexao = new Conexao();
    Session session = Conexao.getSessionFactory().openSession(); 

    public void CadastrarCarro(Carros carro) {

        this.mensagem = "";

        try {
            session.beginTransaction();
            session.save(carro);
            session.getTransaction().commit();
            session.close();
            this.mensagem = "Cadastro efetuado";
        } catch (HibernateException e) {
            this.mensagem = e.getMessage();
        }
    }

    public Carros PesquisarCarro(Carros carro) {
        this.mensagem = "";
        Query query = session.createQuery("from Carros c where c.id = :id");
        query.setParameter("id", carro.getId());

        if (query.list().size() < 1) {
            this.mensagem = "Não existe este ID";
        } else {
            carro = (Carros) query.list().get(0);
        }

        return carro;
    }

Since you are here to learn, try reading the first method and make the delete and update one. Believe me, it's not hard at all. If you just can't do it, clone the source code or just go where the DAO class is and find out the boring way. 

That's it for the DAL package. 

### Modelo Package classes

If you are following step by step, this package should be created already. So I will not even bother telling you to create one.
This package is were all your logic belongs, here we validate, build and control were things go and what order they should be done. 

Let's make the **Validacao** class. 
1. Create a new java class for it.

Put this code inside. 

     public String mensagem;
     public int id;
     public BigDecimal valor;

    public void ValidaCarro(List<String> dadosCarro) {
        this.mensagem = "";
        if (dadosCarro.get(0).length() < 3) 
            this.mensagem += "Fabricante com menos de 3 caracteres \n";
        
        if (dadosCarro.get(1).length() < 3) 
            this.mensagem += "Modelo com menos de 3 caracteres \n";       
        
        if (dadosCarro.get(2).length() > 4)
            this.mensagem += "Ano não pode ser maior que 4 caracteres \n";        
    }
    
    public void ValidarId(String id){
        this.mensagem = "";
        try {
            this.id = Integer.parseInt((id));
        } catch (Exception e) {
            this.mensagem = "Id Inválido";
        }
    }
    
    public void ValidarValor(String valor){
        this.mensagem = "";
        try {
            BigDecimal bigDecimal = new BigDecimal(valor);
            this.valor = bigDecimal;
        } catch (Exception e) {
            this.mensagem = "Valor inválido";
        }
    }

Put as many validations as you pleased

Let's make the **Controle** class
1. Create a new java class

Put this code:

    public String mensagem;   
    
    public void CadastrarCarro(List<String> dadosCarro){       
        this.mensagem = "";
        
        Carros carro = new Carros();
        Validacao validacao = new Validacao();
        
        validacao.ValidaCarro(dadosCarro);
        validacao.ValidarValor(dadosCarro.get(3));
        
        if(validacao.mensagem.equals("")){
            
            carro.setId(0);
            carro.setFabricante(dadosCarro.get(0));
            carro.setModelo(dadosCarro.get(1));
            carro.setAno(dadosCarro.get(2));
            carro.setValor(validacao.valor);
            
            CarroDAO carroDAO = new CarroDAO();
            carroDAO.CadastrarCarro(carro);
            this.mensagem = carroDAO.mensagem;
        }
        else
            this.mensagem = validacao.mensagem;
       }
    
    
As you can see, it is quite simple. First you validate your data, then you create your Carro object and call the DAO method. 
Just like the DAO part, Here you will need to create a method for each CRUD letter. We already have the C, so sit down, learn how to code and make the RUD methods. It's literally the same code. 

After that reality shock, we have ended the **Modelo** package. 

### ManagedBeans package

The managedBeans is where you connect the front with the back, it's the bridge between then. Here is where you call the **Controle** class that calls everything else. So, let's get going.

1. Create the package **"ManagedBeans"**
2. Create a new class for it. *Add > Others > JavaServer Faces > Managed Bean JSF* 
3. Call it **bgrCadastro**

Inside of it you put this ~~very small~~ code.

    @Named(value = "bgrCadastro")
    @ManagedBean
    @RequestScoped
    public class bgrCadastro {

	    private String fabricante;
	    private String modelo;
	    private String ano;
	    private String preco;
	    private String mensagem;
	    
	    public bgrCadastro() { }
	    
	    public String cadastrarCarro(){
	        Controle controle = new Controle();
	        
	        List<String> dadosCarro = new ArrayList();
	        dadosCarro.add(fabricante);
	        dadosCarro.add(modelo);
	        dadosCarro.add(ano);   
	        dadosCarro.add(preco);
	                
	        controle.CadastrarCarro(dadosCarro);
	        this.mensagem = controle.mensagem;
	        return "/Paginas/RespostaCadastro.xhtml";
	    }

	    public String getFabricante() {
	        return fabricante;
	    }

	    public void setFabricante(String fabricante) {
	        this.fabricante = fabricante;
	    }

	    public String getModelo() {
	        return modelo;
	    }

	    public void setModelo(String modelo) {
	        this.modelo = modelo;
	    }

	    public String getAno() {
	        return ano;
	    }

	    public void setAno(String ano) {
	        this.ano = ano;
	    }

	    public String getPreco() {
	        return preco;
	    }

	    public void setPreco(String preco) {
	        this.preco = preco;
	    }

	    public String getMensagem() {
	        return mensagem;
	    }

	    public void setMensagem(String mensagem) {
	        this.mensagem = mensagem;
	    }
    }
Don't worry, a lot of it is automatic. I tell you how to do this, but first, you have to consider some things:
1. You have to add @Named(value = "bgrCadastro")
2. To be able to call any method from the web page, the method **must** start with lower case
3. You need to create the constructor for it. It's the `public brgCadastro() { }` line.

Now, to write almost everything automatic just follow:

1. Right click inside the class
2. Go to **insert code > Getter and Setter** and check all the boxes 
3. Click generate and there is the magic. ~~Quite magical hum?~~ 

Now you can create the **bgrPEE** so go ahead and create one. Then you can add this code in it:

    public String pesquisarFabricante(){
        Controle controle = new Controle();
        List<String> dadosCarro = new ArrayList();
        listaCarros = new ArrayList();
        dadosCarro.add("0");
        dadosCarro.add(this.fabricante);
        dadosCarro.add("");
        dadosCarro.add("");
        listaCarros = controle.PesquisarCarroPorFabricante(dadosCarro);
        
        this.mensagem = controle.mensagem;
        if(listaCarros == null){
            this.mensagem = "Não existe nenhum fabricante com esse nome";
            return null;
        }
        else{
            if (listaCarros.size() == 1){
                this.id = listaCarros.get(0).getId().toString();
                this.fabricante = listaCarros.get(0).getFabricante().toString();
                this.modelo = listaCarros.get(0).getModelo().toString();
                this.ano = listaCarros.get(0).getAno().toString();
                this.preco = listaCarros.get(0).getValor().toString();
                return null;
            }
            else
                return "/Paginas/RespostaPEE.xhtml";
        }                
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<Carros> getListaCarros() {
        return listaCarros;
    }

    public void setListaCarros(List<Carros> listaCarros) {
        this.listaCarros = listaCarros;
    }

Just a reminder

- Methods here should start with lower case
- you need to add @Named(value = "bgrPEE") at the beggining
- The getters and setters are created just for the things you gonna use in the PEE.xhtml.

## Java Web, a ~~bad~~ way to web dev

Finally we are at the end of the Exam, took a very long time to write all those lines... But here we are, doing a favor for you guys and girls.

Unfortunaly, it is 00:43 AM, and ~~I'm just too tired to try figure out~~ I just can't figure out a way to insert the code here, without any breaking changes. So here, I say: Clone the git repository or just navegate to it and find out what you need to write in the web pages.

A quick summary for the pages:
1. All the files are JSF Pages
2. You need to edit the index.xhtml
3. You need to create the **Paginas** folder and add a few more JSF Pages
4. You can create a CSS file to make it look better.

If you have any question about the code, you can contact me. I will happily explain to you the source code.
That is all for the ALPOO's EXAM. **Good luck!**

