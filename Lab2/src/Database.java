import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Group;
import model.Product;

public class Database {
    private static Statement statement;

    public Database(Statement statement) {
        this.statement = statement;
    }

    public void insertNewGroup(String name,String description){
        String query =  "INSERT INTO `ProductGroup` (`name`, `description`) VALUES (\""+name+"\", \""+description+"\")";
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id){
        String query = "DELETE FROM `Product` WHERE `id`="+id;
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGroup(int id){
        String query1 ="DELETE FROM `Product` WHERE group_id="+id;
        String query2 ="DELETE FROM `ProductGroup` where id="+id;

        try {
            statement.execute(query1);
            statement.execute(query2);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public ArrayList<Group> getGrpoups(){
        ArrayList<Group> arrayList = new ArrayList();
        String query = "Select * from `ProductGroup`";
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                Group group = new Group(rs.getInt("id"),rs.getString("name"),rs.getString("description"));
                arrayList.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public void updateGroupyById(int id, String name, String description){
        String query = "UPDATE `ProductGroup` SET `name` = '"+name+"', `description`='"+description+"' WHERE `ProductGroup`.`id`="+id;
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Group getGroupByName(String name){
        String query = "Select * from `ProductGroup` where name =" +name;
        Group group=null;
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                group = new Group(rs.getInt("id"),rs.getString("name"),rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    public void insertNewProduct(int group_id, String name,String description, String producer, int number, int cost){
        String query =  "INSERT INTO `Product` (`group_id`, `name`, `description`,`producer`,`number`,`cost`) VALUES (\""+group_id+"\", \""+name+"\", \""+description+"\", \""+producer+"\", \""+number+"\", \""+cost+"\")";
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Product> getProducts(){
        ArrayList<Product> arrayList = new ArrayList();
        String query = "Select * from `Product`";
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                Product product = new Product(rs.getInt("id"),rs.getInt("group_id"),rs.getString("name"),rs.getString("description"), rs.getString("producer"), rs.getInt("number"), rs.getInt("cost"));
                arrayList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public void updateProductById(int id,int group_id, String name, String description, String producer, int number, int cost){
        String query = "UPDATE `Product` SET `group_id` = '"+group_id+"', `name` = '"+name+"', `description` = '"+description+"', `producer` = '"+producer+"', `number` = '"+number+"',  `cost` = '"+cost+"' WHERE `Product`.`id` = "+id;
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Product> findPerson(int group_id, String name, String producer, int number, int cost){
        ArrayList<Product> products= getProducts();
        if (group_id!=0) products= findPersonByGroupId(products, group_id);
        if (!name.equals("")) products=findPersonByName(products, name);
        if (!producer.equals("")) products=findPersonByProducer(products, producer);
        if (number!=-1) products= findPersonByNumber(products, number);
        if (cost!=-1) products= findPersonByCost(products, cost);
        return products;
    }

    //All next methods should be used just in findPeople();


    private ArrayList<Product> findPersonByGroupId(ArrayList<Product> products, int group_id){
        ArrayList<Product> productArrayList= new ArrayList<>();
        for (int i=0;i<products.size();i++){
            if (products.get(i).getGroup_id()==group_id){
                productArrayList.add(products.get(i));
            }
        }
        return productArrayList;
    }


    private ArrayList<Product> findPersonByName(ArrayList<Product> products, String  name){
        ArrayList<Product> productArrayList= new ArrayList<>();
        for (int i=0;i<products.size();i++){
            if (products.get(i).getName().equals(name)){
                productArrayList.add(products.get(i));
            }
        }
        return productArrayList;
    }


    private ArrayList<Product> findPersonByProducer(ArrayList<Product> products, String producer){
        ArrayList<Product> productArrayList= new ArrayList<>();
        for (int i=0;i<products.size();i++){
            if (products.get(i).getProducer().equals(producer)){
                productArrayList.add(products.get(i));
            }
        }
        return productArrayList;
    }

    private ArrayList<Product> findPersonByNumber(ArrayList<Product> products, int number){
        ArrayList<Product> productArrayList= new ArrayList<>();
        for (int i=0;i<products.size();i++){
            if (products.get(i).getNumber()==number){
                productArrayList.add(products.get(i));
            }
        }
        return productArrayList;
    }


    private ArrayList<Product> findPersonByCost(ArrayList<Product> products, int cost){
        ArrayList<Product> productArrayList= new ArrayList<>();
        for (int i=0;i<products.size();i++){
            if (products.get(i).getCost()==cost){
                productArrayList.add(products.get(i));
            }
        }
        return productArrayList;
    }

}
