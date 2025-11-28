package library.autosave;

import library.model.Library;

public class AutoSaveTask implements Runnable 
{
    private Library library;

    public AutoSaveTask(Library library) 
    {
        this.library = library;
    }

    @Override
    public void run() 
    {
        while (true) 
        {
            try 
            {
                Thread.sleep(10000);
                library.saveData();
                
            } 
            catch (InterruptedException e) 
            {
                System.out.println("AutoSave stopped.");
                break;
            }
        }
    }
}
