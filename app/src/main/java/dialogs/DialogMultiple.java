package dialogs;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.autochip.myvehicle.EmissionFragment;
import com.autochip.myvehicle.InsuranceFragment;
import com.autochip.myvehicle.MainActivity;
import com.autochip.myvehicle.OnImageUtilsListener;
import com.autochip.myvehicle.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import app_utility.SharedPreferenceClass;

import static com.autochip.myvehicle.MainActivity.PICTURE_REQUEST_CODE;


public class DialogMultiple implements OnImageUtilsListener {
    private Activity aActivity;
    private File sdImageMainDirectory;

    public static OnImageUtilsListener mListener;

    DialogDataStorage dataStorage;

    private OnImageUtilsListener onImageUtilsListener;

    public Dialog dialog;
    private Button btnDone;
    private TextView tvStartDateValue, tvExpiryDateValue, tvRemainderDateValue, tvTitle;
    //EditText etCustomOne, etCustomTwo, etCustomThree;
    private TextInputLayout etCustomOne, etCustomTwo, etCustomThree;
    private LinearLayout llDate, llDateValue;
    private final Calendar myCalendar = Calendar.getInstance();
    private Uri outputFileUri;
    private ImageView ivPreview;

    SharedPreferenceClass sharedPreferenceClass;

    TextView tvStartDate;
    TextView tvExpiryDate;
    TextView tvRemainderDate;
    int nCase;


    public DialogMultiple(Activity aActivity, int nCase, OnImageUtilsListener onImageUtilsListener) {
        this.aActivity = aActivity;
        this.nCase = nCase;
        this.onImageUtilsListener = onImageUtilsListener;
        //if(aActivity!=null)
        //onCreate(nCase);
    }

    public void onCreate(final int nCase) {
        mListener = this;

        dialog = new android.app.Dialog(aActivity, R.style.CustomDialogTheme90);
        dialog.setContentView(R.layout.dialog_add_allinone);
        dialog.setCancelable(true);

        ivPreview = dialog.findViewById(R.id.iv_preview);

        etCustomOne = dialog.findViewById(R.id.et_custom_one);
        etCustomTwo = dialog.findViewById(R.id.et_custom_two);
        etCustomThree = dialog.findViewById(R.id.et_custom_three);
        //etCustomParent = dialog.findViewById(R.id.et_parent);
        tvStartDate = dialog.findViewById(R.id.tv_date_one);
        tvExpiryDate = dialog.findViewById(R.id.tv_date_two);
        tvRemainderDate = dialog.findViewById(R.id.tv_date_three);
        tvTitle = dialog.findViewById(R.id.tv_title);
        tvStartDateValue = dialog.findViewById(R.id.tv_sd_value);
        tvExpiryDateValue = dialog.findViewById(R.id.tv_ed_value);
        tvRemainderDateValue = dialog.findViewById(R.id.tv_rd_value);
        btnDone = dialog.findViewById(R.id.btn_done);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataStorage = new DialogDataStorage(nCase);

            }
        });

        List<String> hintList;
        switch (nCase) {
            case 1: //Insurance
                hintList = Arrays.asList(aActivity.getResources().getStringArray(R.array.insurance_array));
                etCustomThree.setVisibility(View.GONE);
                tvExpiryDate.setVisibility(View.VISIBLE);
                tvRemainderDate.setVisibility(View.VISIBLE);

                //etParentCustom.setHint(hintList.get(1));
                tvTitle.setText(hintList.get(0));
                etCustomOne.setHint(hintList.get(1));
                etCustomTwo.setHint(hintList.get(2));
                tvStartDate.setHint(hintList.get(3));
                tvExpiryDate.setHint(hintList.get(4));
                tvRemainderDate.setHint(hintList.get(5));
                break;
            case 2: //Emission
                hintList = Arrays.asList(aActivity.getResources().getStringArray(R.array.emission_array));
                etCustomThree.setVisibility(View.GONE);
                tvExpiryDate.setVisibility(View.VISIBLE);
                tvRemainderDate.setVisibility(View.VISIBLE);

                tvTitle.setText(hintList.get(0));
                etCustomOne.setHint(hintList.get(1));
                etCustomTwo.setHint(hintList.get(2));
                tvStartDate.setHint(hintList.get(3));
                tvExpiryDate.setHint(hintList.get(4));
                tvRemainderDate.setHint(hintList.get(5));
               /* etCustomOne = DialogMultiple.findViewById(R.id.et_custom_one);
                etCustomTwo = DialogMultiple.findViewById(R.id.et_custom_two);
                etCustomParent = DialogMultiple.findViewById(R.id.et_parent);
                tvStartDate = DialogMultiple.findViewById(R.id.tv_start_date);
                tvExpiryDate = DialogMultiple.findViewById(R.id.tv_expiry_date);
                tvRemainderDate = DialogMultiple.findViewById(R.id.tv_remainder_date);*/
                break;
            case 3: //Rc/Fc
                hintList = Arrays.asList(aActivity.getResources().getStringArray(R.array.rc_fc_array));
                etCustomThree.setVisibility(View.VISIBLE);
                tvExpiryDate.setVisibility(View.GONE);
                tvRemainderDate.setVisibility(View.GONE);

                tvTitle.setText(hintList.get(0));
                etCustomOne.setHint(hintList.get(1));
                etCustomTwo.setHint(hintList.get(2));
                etCustomThree.setHint(hintList.get(3));
                tvStartDate.setHint(hintList.get(4));
                /*etCustomOne = DialogMultiple.findViewById(R.id.et_custom_one);
                etCustomTwo = DialogMultiple.findViewById(R.id.et_custom_two);
                etCustomThree = DialogMultiple.findViewById(R.id.et_custom_three);
                etCustomParent = DialogMultiple.findViewById(R.id.et_parent);
                tvStartDate = DialogMultiple.findViewById(R.id.tv_start_date);*/
                break;
            case 4: //Service History
                hintList = Arrays.asList(aActivity.getResources().getStringArray(R.array.service_array));
                etCustomThree.setVisibility(View.VISIBLE);
                tvExpiryDate.setVisibility(View.VISIBLE);
                tvRemainderDate.setVisibility(View.VISIBLE);

                tvTitle.setText(hintList.get(0));
                etCustomOne.setHint(hintList.get(1));
                etCustomTwo.setHint(hintList.get(2));
                etCustomThree.setHint(hintList.get(3));
                tvStartDate.setHint(hintList.get(4));
                tvExpiryDate.setHint(hintList.get(5));
                tvRemainderDate.setHint(hintList.get(6));
                /*etCustomOne = DialogMultiple.findViewById(R.id.et_custom_one);
                etCustomTwo = DialogMultiple.findViewById(R.id.et_custom_two);
                etCustomThree = DialogMultiple.findViewById(R.id.et_custom_three);
                etCustomParent = DialogMultiple.findViewById(R.id.et_parent);
                tvStartDate = DialogMultiple.findViewById(R.id.tv_start_date);
                tvExpiryDate = DialogMultiple.findViewById(R.id.tv_expiry_date);
                tvRemainderDate = DialogMultiple.findViewById(R.id.tv_remainder_date);*/
                break;
        }

        llDate = dialog.findViewById(R.id.ll_date);
        llDateValue = dialog.findViewById(R.id.ll_date_value);
        ivPreview = dialog.findViewById(R.id.iv_preview);
        TextView tvCamera = dialog.findViewById(R.id.tv_camera);

        /*EditText etCustomOne = DialogMultiple.findViewById(R.id.et_custom_one);
        EditText etCustomTwo = DialogMultiple.findViewById(R.id.et_custom_two);
        EditText etCustomThree = DialogMultiple.findViewById(R.id.et_custom_three);

        tvStartDate = DialogMultiple.findViewById(R.id.tv_start_date);
        tvExpiryDate = DialogMultiple.findViewById(R.id.tv_expiry_date);
        tvRemainderDate = DialogMultiple.findViewById(R.id.tv_remainder_date);

        TextView tvCamera = DialogMultiple.findViewById(R.id.tv_camera);

        tvStartDateValue = DialogMultiple.findViewById(R.id.tv_sd_value);
        tvExpiryDateValue = DialogMultiple.findViewById(R.id.tv_ed_value);
        tvRemainderDateValue = DialogMultiple.findViewById(R.id.tv_rd_value);

        ivPreview = DialogMultiple.findViewById(R.id.iv_preview);*/

        View.OnClickListener l = new View.OnClickListener() {
            TextView textView;

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tv_date_one:
                        textView = tvStartDateValue;
                        break;
                    case R.id.tv_date_two:
                        textView = tvExpiryDateValue;
                        break;
                    case R.id.tv_date_three:
                        textView = tvRemainderDateValue;
                        break;
                    case R.id.tv_sd_value:
                        textView = tvStartDateValue;
                        break;
                    case R.id.tv_ed_value:
                        textView = tvExpiryDateValue;
                        break;
                    case R.id.tv_rd_value:
                        textView = tvRemainderDateValue;
                        break;
                    /*case R.id.btn_done:
                        dialog.dismiss();
                        break;*/
                }

                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateLabel(textView);
                    }

                };
                DatePickerDialog dialog = new DatePickerDialog(aActivity, date,
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        };

        tvStartDate.setOnClickListener(l);
        tvExpiryDate.setOnClickListener(l);
        tvRemainderDate.setOnClickListener(l);
        tvStartDateValue.setOnClickListener(l);
        tvExpiryDateValue.setOnClickListener(l);
        tvRemainderDateValue.setOnClickListener(l);


        tvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageIntent();
            }
        });
    }

    //checks for required fields and information that is filled by user
    private boolean validateInfo(String info, int totalFields) {
        String[] saVerify = info.split(",");
        if(saVerify.length == totalFields) {
            for (int i = 1; i < saVerify.length; i++) {
                if (TextUtils.isEmpty(saVerify[i])) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    private void updateLabel(TextView textView) {
        llDate.setGravity(Gravity.END);
        llDateValue.setVisibility(View.VISIBLE);
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        textView.setText(sdf.format(myCalendar.getTime()));
    }

    private void openImageIntent() {
        //onImageUtilsListener.onBitmapCompressed("SHOW_PROGRESS_BAR",1,null, null, null);
        MainActivity.homeInterfaceListener.onHomeCalled("SHOW_PROGRESS_BAR", nCase, null, null);

        // Determine Uri of camera image to save.
        final File root = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "Android/data/" + File.separator + aActivity.getPackageName() + File.separator);
        root.mkdirs();
        final String fname = System.currentTimeMillis() + "insurance";
        sdImageMainDirectory = new File(root, fname);
        outputFileUri = Uri.fromFile(sdImageMainDirectory);

        // Camera.
        final List<Intent> cameraIntents = new ArrayList<>();
        final Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = aActivity.getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(packageName);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            cameraIntents.add(intent);
        }

        // Filesystem.
        final Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_PICK);

        // Chooser of filesystem options.
        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Choose");

        // Add the camera options.
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[]{}));
        MainActivity.homeInterfaceListener.onHomeCalled("FILE_URI", nCase, null, outputFileUri);
        aActivity.startActivityForResult(chooserIntent, PICTURE_REQUEST_CODE);
        //onImageUtilsListener.onBitmapCompressed("START_ACTIVITY_FOR_RESULT",1,null, chooserIntent, outputFileUri);
    }

    @Override
    public void onBitmapCompressed(String sMessage, int nCase, Bitmap bitmap, Intent intent, Uri outputFileUri) {
        switch (sMessage) {
            case "SET_BITMAP":
                ivPreview.setImageBitmap(bitmap);
                break;
        }

    }

    class DialogDataStorage {

        private DialogDataStorage(int type) {
            storeTask(type);
        }

        //insurance related
        String sInsuranceDocNo;
        String sVendorName;
        String insuranceStartDate;
        String insuranceEndDate;
        String insuranceRemainderDate;
        Bitmap bitmapInsurance;

        //Emission related
        String sEmissionDocNo;
        String sAgencyName;
        String emissionStartDate;
        String emissionEndDate;
        String emissionRemainderDate;
        Bitmap bitmapEmission;

        void storeTask(int nType) {
            StringBuilder sb;
            sharedPreferenceClass = new SharedPreferenceClass(aActivity);
            switch (nType) {
                case 1:
                    sb = new StringBuilder();
                    sInsuranceDocNo = etCustomOne.getEditText().getText().toString();
                    sb.append(sInsuranceDocNo).append(",");
                    sVendorName = etCustomTwo.getEditText().getText().toString();
                    sb.append(sVendorName).append(",");
                    insuranceStartDate = tvStartDateValue.getText().toString();
                    sb.append(insuranceStartDate).append(",");
                    insuranceEndDate = tvExpiryDateValue.getText().toString();
                    sb.append(insuranceEndDate).append(",");
                    insuranceRemainderDate = tvRemainderDateValue.getText().toString();
                    sb.append(insuranceRemainderDate);

                    if (validateInfo(sb.toString().trim(), 5)) {
                        sharedPreferenceClass.setInsuranceData(sb.toString());
                        InsuranceFragment.mListener.onInteraction("UPDATE_TABLE_ROW", nType, sb.toString());
                        dialog.dismiss();
                    } else {
                        Toast.makeText(aActivity, "Please fill all the information", Toast.LENGTH_SHORT).show();
                    }
                    //bitmapInsurance =((BitmapDrawable)ivPreview.getDrawable()).getBitmap();
                    break;
                case 2:
                    sb = new StringBuilder();
                    sEmissionDocNo = etCustomOne.getEditText().getText().toString();
                    sb.append(sEmissionDocNo).append(",");
                    sAgencyName = etCustomTwo.getEditText().getText().toString();
                    sb.append(sAgencyName).append(",");
                    emissionStartDate = tvStartDateValue.getText().toString();
                    sb.append(emissionStartDate).append(",");
                    emissionEndDate = tvExpiryDateValue.getText().toString();
                    sb.append(emissionEndDate).append(",");
                    emissionRemainderDate = tvRemainderDateValue.getText().toString();
                    sb.append(emissionRemainderDate);

                    if (validateInfo(sb.toString().trim(), 5)) {
                        sharedPreferenceClass.setEmissionData(sb.toString());
                        EmissionFragment.mListener.onInteraction("UPDATE_TABLE_ROW", nType, sb.toString());
                        dialog.dismiss();
                    } else {
                        Toast.makeText(aActivity, "Please fill all the information", Toast.LENGTH_SHORT).show();
                    }
                    //bitmapEmission =((BitmapDrawable)ivPreview.getDrawable()).getBitmap();
                    break;
            }
        }
    }
}
