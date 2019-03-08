package Utils;

import Domain.Tema;

public class TemaChangeEvent implements Event{

    private ChangeEventType type;
    private Tema data, oldData;

    public TemaChangeEvent(ChangeEventType type, Tema data) {
        this.type = type;
        this.data = data;
    }
    public TemaChangeEvent(ChangeEventType type, Tema data, Tema oldData) {
        this.type = type;
        this.data = data;
        this.oldData=oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Tema getData() {
        return data;
    }

    public Tema getOldData() {
        return oldData;
    }
}