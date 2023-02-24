package Components.Booking;

public class Booking {
    private int room_id;
    private int resident_id;
    private String name;
    private String email;

    public Booking() {

    }

    public Booking(int room_id, int resident_id, String name, String email) {
        this.room_id = room_id;
        this.name = name;
        this.email = email;
        this.resident_id = resident_id;
    }

    public int getRoomNo() {
        return room_id;
    }

    public String getCustName() {
        return name;
    }

    public String getCustEmail() {
        return email;
    }

    public int getCustid() {
        return resident_id;
    }
}
