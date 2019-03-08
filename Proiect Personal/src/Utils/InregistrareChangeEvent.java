package Utils;

import Domain.Inregistrare;

public class InregistrareChangeEvent implements Event{

    private ChangeEventType type;
    private Inregistrare data, oldData;

    public InregistrareChangeEvent(ChangeEventType type, Inregistrare data) {
        this.type = type;
        this.data = data;
    }
    public InregistrareChangeEvent(ChangeEventType type, Inregistrare data, Inregistrare oldData) {
        this.type = type;
        this.data = data;
        this.oldData=oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Inregistrare getData() {
        return data;
    }

    public Inregistrare getOldData() {
        return oldData;
    }
}