package com.cengiz.yazlabandroidwebapplication;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.lang.Object;
import org.apache.http.client.HttpResponseException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class Listele extends Activity{
	
	ImageView img ;
	TextView tx_evIL;
	TextView tx_evEmlakTipi;
	TextView tx_evAlan;
	TextView tx_evOdaSayisi;
	TextView tx_evBinaYasi;
	TextView tx_evBulKat;
	TextView tx_evFiyat;
	TextView tx_evAciklama;
	ArrayList<Gelen> GelenListe;
	int GelenSayisi;
	ListView liste;
	Gelen ge;
	int secilen_id_int ;

	String  listItemIL[] ;
	String  listItemEmlakTip[] ;
	String  listItemAlan[] ;
	String  listItemOdaSayisi[] ;
	String  listItemBinaYasi[] ;
	String  listItemBulKat[] ;
	String  listItemFiyat[] ;
	String  listItemAciklama[] ;
	String  listItemResimYol[] ;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.sayfa2);
	        img = (ImageView) findViewById(R.id.imageView1);
	        tx_evIL = (TextView) findViewById(R.id.tx_il);
	        tx_evEmlakTipi = (TextView) findViewById(R.id.tx_Emlak_Tip);
	        tx_evAlan = (TextView) findViewById(R.id.tx_Alan);
	        tx_evOdaSayisi = (TextView) findViewById(R.id.tx_Oda_sayisi);
	        tx_evBinaYasi = (TextView) findViewById(R.id.tx_BinaYasi);
	        tx_evBulKat = (TextView) findViewById(R.id.tx_Bul_Kat);
	        tx_evFiyat = (TextView) findViewById(R.id.tx_Fiyat);
	        tx_evAciklama = (TextView) findViewById(R.id.tx_Aciklama);
	        
	        Intent intent = getIntent();
	    	String secilen_id_string = intent.getStringExtra("EvID");
	        secilen_id_int = Integer.parseInt(secilen_id_string) ;


	    
	    	
	        
	        CelsiusAsync celsiustofahr = new CelsiusAsync();    
	        celsiustofahr.execute();
	        
	 }	 
	 private class CelsiusAsync extends AsyncTask<Void, Void, Void> {

		    @Override    
		    protected Void doInBackground(Void... params) {
		   
		    		   GelenListe = null;
		           SoapObject request = new SoapObject(Baglanti.NAMESPACE,Baglanti.METHOD_NAME_goster);
		           request.addProperty("id",secilen_id_int );
		        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		        envelope.dotNet = true;
		        envelope.setOutputSoapObject(request);
		        HttpTransportSE androidHttpTransport = new HttpTransportSE(Baglanti.URL_VeritabaniService);
		        androidHttpTransport.debug = true;
		        try
		        {
		           androidHttpTransport.call(Baglanti.SOAP_ACTION_goster, envelope);
		      //     Log.d("dump Request: " ,androidHttpTransport.requestDump);
		       //    Log.d("dump response: " ,androidHttpTransport.responseDump);
		         SoapObject response = (SoapObject) envelope.getResponse();
		        //   Object response =envelope.getResponse();

		         GelenListe=new ArrayList<Gelen>();
		         listItemIL = new String[response.getPropertyCount()];
		         listItemEmlakTip = new String[response.getPropertyCount()];
		         listItemAlan = new String[response.getPropertyCount()];
		         listItemOdaSayisi = new String[response.getPropertyCount()];
		         listItemBinaYasi= new String[response.getPropertyCount()];
		         listItemBulKat = new String[response.getPropertyCount()];
		         listItemFiyat = new String[response.getPropertyCount()];
		         listItemAciklama = new String[response.getPropertyCount()];
		         listItemResimYol = new String[response.getPropertyCount()];
			       
			  
		           
		                   for(int i=0;i<(response.getPropertyCount());i++)
		                   {
		                	    ge=new Gelen();
		                	   SoapObject randevu = (SoapObject) response.getProperty(i);

		                	   ge.setEvID(Integer.parseInt(randevu.getProperty("evID").toString()));
		                	   ge.setEvIL(randevu.getProperty("evIL").toString());
		                	   listItemIL[i]  = String.valueOf(ge.getEvIL().toString());
		                	   ge.setEvEmlakTip(randevu.getProperty("evEmlakTip").toString());
		                	   listItemEmlakTip[i]  = String.valueOf(ge.getEvEmlakTip().toString());
		                	   ge.setEvAlan(Integer.parseInt(randevu.getProperty("evAlan").toString()));
		                	   listItemAlan[i]  = String.valueOf(ge.getEvAlan());
		                	   ge.setEvOdaSayisi(Integer.parseInt(randevu.getProperty("evOdaSayisi").toString()));
		                	   listItemOdaSayisi[i]  = String.valueOf(ge.getEvOdaSayisi());
		                	   ge.setEvBinaYasi(Integer.parseInt(randevu.getProperty("evBinaYasi").toString()));
		                	   listItemBinaYasi[i]  = String.valueOf(ge.getEvBinaYasi());
		                	   ge.setEvBulKat(Integer.parseInt(randevu.getProperty("evBulKat").toString()));
		                	   listItemBulKat[i]  = String.valueOf(ge.getEvBulKat());
		                	   ge.setEvFiyat(Float.parseFloat(randevu.getProperty("evFiyat").toString()));
		                	   listItemFiyat[i]  = String.valueOf(ge.getEvFiyat());
		                	   ge.setEvAciklama(randevu.getProperty("evAciklama").toString());
		                	   listItemAciklama[i]  = String.valueOf(ge.getEvAciklama().toString());
		                	   ge.setResimID(Integer.parseInt(randevu.getProperty("resimID").toString()));
		                	   ge.setResimYOL(randevu.getProperty("resimYOL").toString());
		                	   listItemResimYol[i]  = String.valueOf(ge.getResimYOL().toString());

		                	   ge.setResimEvID(Integer.parseInt(randevu.getProperty("resimEvID").toString()));
		    
		                           GelenListe.add(ge);
		                }
		                   GelenSayisi=Integer.parseInt(String.valueOf(response.getPropertyCount()));             
		        
		        
		        
		        
		                   new DownloadImageTask((ImageView) findViewById(R.id.imageView1))
		                   .execute(ge.getResimYOL());
		        
		        }
		        catch (HttpResponseException e) {
						e.printStackTrace();
					} catch (IOException e) {	
						e.printStackTrace();
					} catch (XmlPullParserException e) {
						e.printStackTrace();
					} 
		        return null;      
		     }

		     @Override        
		     protected void onPostExecute(Void aVoid) {
		     super.onPostExecute(aVoid);            
		  //   pdialog.dismiss();   
		     // Burada  0. indisin olmasýnýn sebebi, her seçim iþleminde bir elemanýn bilgileri dönüyor.Boylece listelerde 1 eleman 
		     // olmuþ olur.
		     // Resmin urlsi
		     
		     String img_path = listItemResimYol[0];   
		     StringBuffer sBuffer = new StringBuffer();
		     String sonString;
		     sBuffer.append(img_path);
		     sBuffer.deleteCharAt(0);
		     sonString = sBuffer.toString();
		     Bitmap bmp= decodeSampledBitmapFromPath(sonString,240,240);
		    
		     
		     img.setImageBitmap(bmp);
		     
		     
		     tx_evIL.setText(listItemIL[0]);
		     tx_evEmlakTipi.setText(listItemEmlakTip[0]);
		     tx_evAlan.setText(listItemAlan[0]);
		     tx_evOdaSayisi.setText(listItemOdaSayisi[0]);
		     tx_evBinaYasi.setText(listItemBinaYasi[0]);
		     tx_evBulKat.setText(listItemBulKat[0]);
		     tx_evFiyat.setText(listItemFiyat[0]);
		     tx_evAciklama.setText(listItemAciklama[0]);
		 //  Toast.makeText(getApplicationContext(), , Toast.LENGTH_SHORT).show();        
		     }

		    @Override        
		     protected void onPreExecute() {
		         super.onPreExecute();
		      //   pdialog = new ProgressDialog(MainActivity.this);            
		       //  pdialog.setMessage("Converting...");
		        // pdialog.show();
		    }
		}
	 public static Bitmap decodeSampledBitmapFromPath(String path, int reqWidth,
	            int reqHeight) {

	        final BitmapFactory.Options options = new BitmapFactory.Options();
	        options.inJustDecodeBounds = true;
	        BitmapFactory.decodeFile(path, options);

	        options.inSampleSize = calculateInSampleSize(options, reqWidth,
	                reqHeight);

	        // Decode bitmap with inSampleSize set
	        options.inJustDecodeBounds = false;
	        Bitmap bmp = BitmapFactory.decodeFile(path, options);
	        return bmp;
	        }

	    public static int calculateInSampleSize(BitmapFactory.Options options,
	            int reqWidth, int reqHeight) {

	        final int height = options.outHeight;
	        final int width = options.outWidth;
	        int inSampleSize = 1;

	        if (height > reqHeight || width > reqWidth) {
	            if (width > height) {
	                inSampleSize = Math.round((float) height / (float) reqHeight);
	            } else {
	                inSampleSize = Math.round((float) width / (float) reqWidth);
	             }
	         }
	         return inSampleSize;
	        }
	    
	    
	    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	        ImageView bmImage;

	        public DownloadImageTask(ImageView bmImage) {
	            this.bmImage = bmImage;
	        }

	        protected Bitmap doInBackground(String... urls) {
	            String urldisplay = urls[0];
	            Bitmap mIcon11 = null;
	            try {
	                InputStream in = new java.net.URL(urldisplay).openStream();
	                mIcon11 = BitmapFactory.decodeStream(in);
	            } catch (Exception e) {
	                Log.e("Error", e.getMessage());
	                e.printStackTrace();
	            }
	            return mIcon11;
	        }

	        protected void onPostExecute(Bitmap result) {
	            bmImage.setImageBitmap(result);
	        }
	    }
	    
	    

}
