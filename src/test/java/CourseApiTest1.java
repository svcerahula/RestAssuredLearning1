import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import payload.coursePrice;

public class CourseApiTest1 {

    @Test
    public void validateTotalPriceOfCourses() {
        JsonPath js1 = new JsonPath(coursePrice.returnCoursePriceDetailsJsonString());

        System.out.println("total purchase amount : "+ js1.getInt("dashboard.purchaseAmount"));
        System.out.println("total courses  present : "+ js1.getInt("courses.size()"));

        int totalPurchaseAmount = js1.getInt("dashboard.purchaseAmount");
        int totalCourses = js1.getInt("courses.size()");

        int calcTotalPurchaseAmount=0;
        for(int i=0;i < totalCourses;i++) {
            System.out.println("Topic is :"+ js1.getString("courses["+i+"].title"));
            String title  = js1.getString("courses["+i+"].title");
            System.out.println("Topic Unit Price is :"+ js1.getString("courses["+i+"].price"));
            System.out.println("Topic total copies are :"+ js1.getString("courses["+i+"].copies"));
            int totalForEachTopic = js1.getInt("courses["+i+"].price")
                    * js1.getInt("courses["+i+"].copies");
            System.out.println("Total for Topic "+title+" is : "+totalForEachTopic);
            calcTotalPurchaseAmount = calcTotalPurchaseAmount + totalForEachTopic;
        }

        Assert.assertTrue(calcTotalPurchaseAmount==totalPurchaseAmount,"The calculated Price and total " +
                "Purchase Amount in the json is not equal");
    }
}
