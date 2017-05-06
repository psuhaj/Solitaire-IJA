package solitaire.model.cmder;

/**
 * Interface for commander.
 */
public interface Commander {

    /**
     * Function execute the command.
     *
     * @return     True on success execution of command, false otherwise.
     */
    public boolean execute();

    /**
     * Function provide undo of command.
     */
    public void undo();

}
