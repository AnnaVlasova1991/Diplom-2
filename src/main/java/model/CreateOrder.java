package model;
import java.util.List;

public class CreateOrder {
    private String success;
    private List<Data> data;
    private String name;
    private Order order;
    private List<String> ingredients;
    private String message;

    public CreateOrder(String success, String message) {
        this.success = success;
        this.message = message;
    }

    public CreateOrder(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public CreateOrder(String success, List<Data> data) {
        this.success = success;
        this.data = data;
    }

    public CreateOrder(String success, String name, Order order) {
        this.success = success;
        this.name = name;
        this.order = order;
    }

    public CreateOrder() {

    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
