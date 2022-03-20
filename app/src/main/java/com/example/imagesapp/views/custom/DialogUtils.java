package com.example.imagesapp.views.custom;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;

import com.example.imagesapp.R;
import com.example.imagesapp.views.activities.FullImageActivity;

/**
 * Created by Vadion-Atif on 12/20/2016.
 * DialogUtils is a reusable class to show error and progress dialog throughout the app.
 */

public class DialogUtils {

    private static ProgressDialog progressDialog;

    /**
     * Shows error dialog provided with
     * @param context as activity context and
     * @param message as text to show for error.
     */
    public static void ShowAlert(Context context, String message) {

        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(message);
            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            if(!((Activity)context).isFinishing())
                builder.create().show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//end of method ShowAlert....

    public static void ShowAlert(final Activity context, String message){//, final Class cl) {

        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(message);
            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    /*context.startActivity(new Intent(context,cl));
                    context.finish();*/
                }
            });
            if (!context.isFinishing())
                builder.create().show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//end of method ShowAlert....



    /**
     * Shows a prpgress dialog provided with
     * @param context as activity context and
     * @param message as text to show for progress.
     */
    public static void ShowProgress(Context context, String message) {
        try {
            if (progressDialog != null) {

                progressDialog.setMessage(message);
            } else {
                progressDialog = ProgressDialog.show(context, "", message, true, false);
              //  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    Drawable drawable = new ProgressBar(context).getIndeterminateDrawable().mutate();
                    drawable.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary),
                            PorterDuff.Mode.SRC_IN);
                    progressDialog.setIndeterminateDrawable(drawable);

               // }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hides recently shown progress dialog
     */
    public static void HideDialog() {
        if (progressDialog != null) {
            try {
                progressDialog.dismiss();
                progressDialog = null;
            }
            catch (IllegalArgumentException e)
            {
                e.printStackTrace();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void showErrorDialog(final Activity mContext, String errorMessage) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
       // alertDialog.setTitle("ERROR");
        alertDialog.setMessage(errorMessage);
        alertDialog.setPositiveButton("OK",//mActivity.getString(R.string.error_pin_try_again),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // password = input.getText().toString();
                        try {

                            dialog.dismiss();
                            mContext.finish();

                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.getLocalizedMessage();
                            e.printStackTrace();
                        }
                    }
                });

        /*alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mContext.finish();
                        dialog.dismiss();
                    }
                });*/


        final AlertDialog dialog = alertDialog.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }//end of showErrorDialog....


    public static void showConfirmationDialog(final Activity mContext,String url) {
        try {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

            alertDialog.setTitle("Show Image");
            alertDialog.setMessage("Are you sure you want to check the image?");
        /*final EditText input = setAndGetEdtText();
        alertDialog.setView(input);
        //showKeybord(input);*/

            alertDialog.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            try {

                               Intent intent= new Intent(mContext, FullImageActivity.class);
                               intent.putExtra("url",url);
                               mContext.startActivity(intent);
                               // mContext.finish();
                              //  Constants.CURRENT_TAB_INDEX=Constants.POSITION_NEWS;
                                dialog.dismiss();
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.getLocalizedMessage();
                                e.printStackTrace();
                            }
                        }
                    });
            alertDialog.setNegativeButton("No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            if (!mContext.isFinishing()) {
                alertDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//end of method showConfirmationDialog....

    public static void webViewDialog(Context mContext, String url,String iframe) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
     /*   dialog.setContentView(R.layout.introvid);
        dialog.show();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        lp.copyFrom(dialog.getWindow().getAttributes());
        dialog.getWindow().setAttributes(lp);
        final VideoView videoview = (VideoView)dialog. findViewById(R.id.surface_view);
       // Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.introvideo);
        videoview.setVideoURI(uri);
        videoview.start();*/
        /*AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
        alert.setTitle("Test Video Title");

        WebView wv = new WebView(mContext);

        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }
        });

        alert.setView(wv);
        wv.loadUrl("www.google.com");
        alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alert.show();*/

        AlertDialog.Builder alert = new AlertDialog.Builder(
                mContext);


        alert.setTitle("test");

        WebView wv = new WebView(mContext);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setPluginState(WebSettings.PluginState.ON);
        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wv.getSettings().setSupportMultipleWindows(true);
        wv.getSettings().setSupportZoom(true);
        wv.getSettings().setBuiltInZoomControls(true);
        wv.getSettings().setAllowFileAccess(true);
        wv.loadUrl(url);
        wv.loadDataWithBaseURL(null, "<style>img{display: inline;height: auto;max-width: 100%;}</style>" + iframe, "text/html", "UTF-8", null);
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view,
                                                    String url) {
                view.loadUrl(url);

                return true;
            }
        });

        alert.setNegativeButton("Close",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        Dialog d = alert.setView(wv).create();
        d.show();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(d.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.FILL_PARENT;
        lp.height = WindowManager.LayoutParams.FILL_PARENT;
        d.getWindow().setAttributes(lp);
    }

    public static androidx.appcompat.app.AlertDialog showDialog(Activity mActivity, String title, String msg, String positiveLabel,
                                                                DialogInterface.OnClickListener positiveOnClick,
                                                                String negativeLabel, DialogInterface.OnClickListener negativeOnClick,
                                                                boolean isCancelAble)
    {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(mActivity);
        builder.setTitle(title);
        builder.setCancelable(isCancelAble);
        builder.setMessage(msg);
        builder.setPositiveButton(positiveLabel, positiveOnClick);
        builder.setNegativeButton(negativeLabel, negativeOnClick);

        androidx.appcompat.app.AlertDialog alert = builder.create();
        alert.show();
        return alert;
    }

    public static void showDialog(final Activity mContext, NavController controller,String message) {
        try {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

            alertDialog.setTitle("Confirmation");
            alertDialog.setMessage(message);
        /*final EditText input = setAndGetEdtText();
        alertDialog.setView(input);
        //showKeybord(input);*/

            alertDialog.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                controller.navigateUp();
                                dialog.dismiss();
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.getLocalizedMessage();
                                e.printStackTrace();
                            }
                        }
                    });
            /*alertDialog.setNegativeButton("No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });*/

            if (!mContext.isFinishing()) {
                alertDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//end of method showConfirmationDialog....

}
