package exem.wiev;

import exem.store.FarbaDirectory;

public interface IDialog {
    public void setVisible(boolean b);
    public Object getObject();
    public void setFarbDirectory(FarbaDirectory fd);
    public String toString();
}
