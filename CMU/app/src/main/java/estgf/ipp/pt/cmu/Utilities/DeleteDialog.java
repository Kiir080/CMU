package estgf.ipp.pt.cmu.Utilities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import estgf.ipp.pt.cmu.Database.Interfaces.NotifyRemoveFood;
import estgf.ipp.pt.cmu.Database.Interfaces.NotifyRemoveMeal;
import estgf.ipp.pt.cmu.R;

public class DeleteDialog extends DialogFragment {


    private NotifyRemoveMeal notifyRemoveMeal;
    private NotifyRemoveFood notifyRemoveFood;

    public void setListener(NotifyRemoveMeal notifyRemoveMeal){
        this.notifyRemoveMeal=notifyRemoveMeal;
    }

    public void setListener(NotifyRemoveFood notifyRemoveFood){
        this.notifyRemoveFood=notifyRemoveFood;
    }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(getString(R.string.delete_item))
                    .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if(notifyRemoveMeal !=  null){
                                notifyRemoveMeal.OnRemoveMeal();
                            }else if(notifyRemoveFood != null){
                                notifyRemoveFood.OnRemoveFood();
                            }
                            dismiss();

                        }
                    })
                    .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dismiss();
                        }
                    });
            return builder.create();
        }


}
