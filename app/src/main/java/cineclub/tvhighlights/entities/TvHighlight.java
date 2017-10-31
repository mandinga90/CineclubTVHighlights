package cineclub.tvhighlights.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.Html;
import android.text.Spanned;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import cineclub.tvhighlights.R;
import cineclub.tvhighlights.functional.DateTimeHelper;
import cineclub.tvhighlights.functional.DownloadImageTask;

import static cineclub.tvhighlights.functional.HtmlHelper.replaceHtmlCharacters;

/**
 * Created by mlu on 05.05.2017.
 * Parcelable: http://kaiheinz.de/2014/07/implement-parcelable-android/
 */

public class TvHighlight implements Parcelable {
    private static Map<String,Integer> tvChannelIcons = new HashMap<>();
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    static{
        tvChannelIcons.put("3 Sat", R.drawable.ic_channel_3sat );
        tvChannelIcons.put("3Sat", R.drawable.ic_channel_3sat );
        tvChannelIcons.put("ARD", R.drawable.ic_channel_ard );
        tvChannelIcons.put("Arte", R.drawable.ic_channel_arte );
        tvChannelIcons.put("BR", R.drawable.ic_channel_br );
        tvChannelIcons.put("Comedy", R.drawable.ic_channel_comedy_central );
        tvChannelIcons.put("Das 4.", R.drawable.ic_channel_das_vierte );
        tvChannelIcons.put("Das Vierte", R.drawable.ic_channel_das_vierte );
        tvChannelIcons.put("Disney", R.drawable.ic_channel_disney );
        tvChannelIcons.put("Disney Ch.", R.drawable.ic_channel_disney );
        tvChannelIcons.put("HR", R.drawable.ic_channel_hr );
        tvChannelIcons.put("Kabel 1", R.drawable.ic_channel_kabel_eins );
        tvChannelIcons.put("KiKa", R.drawable.ic_channel_kika );
        tvChannelIcons.put("Kika", R.drawable.ic_channel_kika );
        tvChannelIcons.put("MDR", R.drawable.ic_channel_mdr );
        tvChannelIcons.put("NDR", R.drawable.ic_channel_ndr );
        tvChannelIcons.put("Nick", R.drawable.ic_channel_nickelodeon );
        tvChannelIcons.put("Nickelodeon", R.drawable.ic_channel_nickelodeon );
        tvChannelIcons.put("N-TV", R.drawable.ic_channel_ntv );
        tvChannelIcons.put("One", R.drawable.ic_channel_one );
        tvChannelIcons.put("Phönix", R.drawable.ic_channel_phoenix );
        tvChannelIcons.put("Pro 7", R.drawable.ic_channel_prosieben );
        tvChannelIcons.put("Pro7", R.drawable.ic_channel_prosieben );
        tvChannelIcons.put("Pro 7 MAXX", R.drawable.ic_channel_prosieben_maxx );
        tvChannelIcons.put("Pro7 Maxx", R.drawable.ic_channel_prosieben_maxx );
        tvChannelIcons.put("RBB", R.drawable.ic_channel_rbb );
        tvChannelIcons.put("RTL", R.drawable.ic_channel_rtl );
        tvChannelIcons.put("RTL 2", R.drawable.ic_channel_rtl2 );
        tvChannelIcons.put("RTL II", R.drawable.ic_channel_rtl2 );
        tvChannelIcons.put("RTL2", R.drawable.ic_channel_rtl2 );
        tvChannelIcons.put("RTL Nitro", R.drawable.ic_channel_rtl_nitro );
        tvChannelIcons.put("RTLNitro", R.drawable.ic_channel_rtl_nitro );
        tvChannelIcons.put("Nitro", R.drawable.ic_channel_rtl_nitro );
        tvChannelIcons.put("Sat,1", R.drawable.ic_channel_sat1 );
        tvChannelIcons.put("Sat.1", R.drawable.ic_channel_sat1 );
        tvChannelIcons.put("Sat.1 Gold", R.drawable.ic_channel_sat1_gold );
        tvChannelIcons.put("SF 2", R.drawable.ic_channel_srf_2 );
        tvChannelIcons.put("Sixx", R.drawable.ic_channel_sixx );
        tvChannelIcons.put("Sport 1", R.drawable.ic_channel_sport1 );
        tvChannelIcons.put("Super RTL", R.drawable.ic_channel_super_rtl );
        tvChannelIcons.put("SuperRTL", R.drawable.ic_channel_super_rtl );
        tvChannelIcons.put("SWR", R.drawable.ic_channel_swr );
        tvChannelIcons.put("SWR BW", R.drawable.ic_channel_swr );
        tvChannelIcons.put("Tele 5", R.drawable.ic_channel_tele_5 );
        tvChannelIcons.put("Vox", R.drawable.ic_channel_vox );
        tvChannelIcons.put("WDR", R.drawable.ic_channel_wdr );
        tvChannelIcons.put("ZDF", R.drawable.ic_channel_zdf );
        tvChannelIcons.put("ZDF", R.drawable.ic_channel_zdf );
        tvChannelIcons.put("ZDF NEO", R.drawable.ic_channel_zdf_neo );
        tvChannelIcons.put("ZDFinfo", R.drawable.ic_channel_zdf_info );
        tvChannelIcons.put("ZDFneo", R.drawable.ic_channel_zdf_neo );

    }

    private String dateTime;
    private String tvChannel;
    private String title;
    private String originalTitle;
    private String startDate;
    private String startTyp;
    private int advertisingInMinutes;
    private String link;
    private String imageLink;
    private String genre1;
    private String genre2;
    private String ratingInPercentage;
    private String description;

    public TvHighlight() {
    }

    protected TvHighlight(Parcel in) {
        dateTime = in.readString();
        tvChannel = in.readString();
        title = in.readString();
        originalTitle = in.readString();
        startDate = in.readString();
        startTyp = in.readString();
        advertisingInMinutes = in.readInt();
        link = in.readString();
        imageLink = in.readString();
        genre1 = in.readString();
        genre2 = in.readString();
        ratingInPercentage = in.readString();
        description = in.readString();
    }

    public static final Creator<TvHighlight> CREATOR = new Creator<TvHighlight>() {
        @Override
        public TvHighlight createFromParcel(Parcel in) {
            return new TvHighlight(in);
        }

        @Override
        public TvHighlight[] newArray(int size) {
            return new TvHighlight[size];
        }
    };

    public Calendar getDateTime() {
        Date date = DateTimeHelper.parseJSONDateTime(dateTime);
        Calendar cal = GregorianCalendar.getInstance(/*TimeZone.getTimeZone("GMT+2")*/);
        cal.setTime(date);
        return cal;
    }

    public String getDateTimeString() {
        return timeFormat.format( getDateTime().getTime() );
    }

    public String getTitle(){
        String titleSuffix = "";
        Calendar releaseYearCalendar = getReleaseYear();
            if( releaseYearCalendar != null ){
                int releaseYear = getReleaseYear().get( Calendar.YEAR );
                titleSuffix = " (" + releaseYear + ")";
            }
        return replaceHtmlCharacters(title) + titleSuffix;
    }

    public String getOriginalTitleToBeDisplayed() {
        String originalTitleToBeDisplayed = "";
        if(    ! originalTitle.isEmpty()
            && ! originalTitle.equals(title) ) {
            originalTitleToBeDisplayed = "aka '" + originalTitle + "'";
        }
        return replaceHtmlCharacters(originalTitleToBeDisplayed);
    }

    public int getTvChannelIcon() {
        if( tvChannelIcons.containsKey( tvChannel )){
            return tvChannelIcons.get( tvChannel );
        }
        else{
            return 0;
        }
    }

    public Calendar getReleaseYear(){
        Date date = DateTimeHelper.parseJSONDateTime(startDate);
        if( ! DateTimeHelper.isEmptyDate( date ) ){
            Calendar cal = GregorianCalendar.getInstance();
            cal.setTime(date);
            return cal;
        }
        else{
            return null;
        }
    }

    public String getAdvertisingInMinutesText() {
        StringBuilder advertisingInMinutesTextBuilder = new StringBuilder();
        if( advertisingInMinutes > 0 ){
            advertisingInMinutesTextBuilder.append(advertisingInMinutes);
            advertisingInMinutesTextBuilder.append(" Min. ");
        }
        else{
            advertisingInMinutesTextBuilder.append("Keine ");
        }
        advertisingInMinutesTextBuilder.append("Werbung");
        return advertisingInMinutesTextBuilder.toString();
    }

    public void setCoverImage(ImageView imageView){
        new DownloadImageTask(imageView).execute(imageLink);
    }

    public Spanned getLink() {
        String link = "<a href='" + this.link + "'>Link zur Kritik</a>";
        return Html.fromHtml( link );
    }

    public String getTvChannelName() {
        return tvChannel;
    }

    public String getGenre1() {
        return genre1;
    }

    public String getGenre2() {
        return genre2;
    }

    public String getRatingInPercentage() {
        return ratingInPercentage;
    }

    public String getDescription() {
        return replaceHtmlCharacters(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dateTime);
        dest.writeString(tvChannel);
        dest.writeString(title);
        dest.writeString(originalTitle);
        dest.writeString(startDate);
        dest.writeString(startTyp);
        dest.writeInt(advertisingInMinutes);
        dest.writeString(link);
        dest.writeString(imageLink);
        dest.writeString(genre1);
        dest.writeString(genre2);
        dest.writeString(ratingInPercentage);
        dest.writeString(description);
    }
}
