public class RoomType {
    private int id;
    private  RoomTypeName name ;   //i will use Enum
    private String description ;
    private double basePrice ;


    @Override

    public boolean equals(Object o) {

        if(o==null || !(o instanceof RoomType)) return false;

        RoomType roomType = (RoomType)o ;

        return (id == roomType.getId());
    }

    public RoomType(int id, RoomTypeName name, String description, double basePrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoomTypeName getName() {
        return name;
    }

    public void setName(RoomTypeName name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
}