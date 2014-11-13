package ro.z2h;

//import com.sun.org.apache.xml.internal.security.signature.ObjectContainer;
import com.sun.org.apache.xml.internal.serialize.Printer;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.codehaus.jackson.map.ObjectMapper;
import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.controller.DepartmentController;
import ro.z2h.controller.EmployeeController;
import ro.z2h.fmk.AnnotationScanUtils;
import ro.z2h.fmk.MethodAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class MyDispatcherServlet extends HttpServlet {
    //private ObjectContainer controllere;
    public HashMap<String,MethodAttributes> myMap = new HashMap<String, MethodAttributes>();
    public Long myId;
    @Override
    public void init() throws ServletException {
        try {
            Iterable<Class> classes = AnnotationScanUtils.getClasses("ro.z2h.controller");


            for (Class aClass : classes) {
                System.out.println("Class Name is: "+aClass.getName());
                if(aClass.isAnnotationPresent(MyController.class)){
                    MyController ctrlAnnotation = (MyController)aClass.getAnnotation(MyController.class);
                    System.out.println("URL Path is: "+ctrlAnnotation.urlPath());
                    Method[] myMethods = aClass.getMethods();
                    for (Method myMethod : myMethods) {
                        if(myMethod.isAnnotationPresent(MyRequestMethod.class)){
                            MyRequestMethod mthAnnotation = (MyRequestMethod)myMethod.getAnnotation(MyRequestMethod.class);
                            MethodAttributes myAtrib = new MethodAttributes();
                            myAtrib.setControllerClass(aClass.getName());
                            myAtrib.setMethodName(myMethod.getName());
                            myAtrib.setMethodType(mthAnnotation.methodType());
                            myAtrib.setMethodParameters(myMethod.getParameterTypes());

                            myMap.put(ctrlAnnotation.urlPath()+mthAnnotation.urlPath(),myAtrib);
                            System.out.println("For URL: "+mthAnnotation.urlPath()+" has the method: "+myMethod.toString());

                        }

                    }
                }

            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*DELEGATE TO SOMEONE (an Application Controller)*/
        dispatchReply("GET",req,resp);
        //super.doGet(req, resp);
    }

    private void dispatchReply(String httpMethod, HttpServletRequest req, HttpServletResponse resp) {
            /*DISPATCH()- Delegare catre un Application Controller si returnarea unui raspuns */
        Object r = dispatch(req, resp);

            /* Trainsmiterea raspunsului catre client*/
        try {
            reply(r,req,resp);
        } catch (IOException e) {
            e.printStackTrace();

            /* Transmiterea erori */
            sendException(e,req,resp);
        }
    }

    private void sendException(Exception ex, HttpServletRequest req, HttpServletResponse resp) {
        /*Tratarea exceptiilor*/
    }

    private void reply(Object r,HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        ObjectMapper myOM = new ObjectMapper();



        out.printf(myOM.writeValueAsString(r));

    }


        /*Unde ar trebui apela un app controller*/
    private Object dispatch(HttpServletRequest req, HttpServletResponse resp) {
       String pathInfo = req.getPathInfo();

        //String myString = req.getpara;

        req.getParameterMap();

        MethodAttributes myMethods = myMap.get(pathInfo);
        try {
            if(myMethods != null){
           Class myControllerClass = Class.forName(myMethods.getControllerClass());
           Object controller = myControllerClass.newInstance();
            Method controllerMethod = myControllerClass.getMethod(myMethods.getMethodName(),myMethods.getMethodParameters());
            Object invoke = controllerMethod.invoke(controller,myMethods.getMethodParameters());


            return invoke;}
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        /*if(pathInfo.startsWith("/employee")){
            EmployeeController controller = new EmployeeController();
            return controller.getAllEmployees();
        }
        if(pathInfo.startsWith("/department")){
            DepartmentController controller = new DepartmentController();
            return controller.getAllDepartments();
        }*/

        /* Stabilire controller in functie de pathInfo */



        return "HELLO";
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*DELEGATE TO SOMEONE (an Application Controller)*/
        dispatchReply("POST",req,resp);
        //super.doPost(req, resp);
    }



}
