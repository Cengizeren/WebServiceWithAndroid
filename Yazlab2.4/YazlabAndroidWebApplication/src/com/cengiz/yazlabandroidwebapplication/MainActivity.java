package com.cengiz.yazlabandroidwebapplication;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.HttpResponseException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;





import android.app.Activity;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;



public class MainActivity extends Activity {
 	

	TextView textView;
	ArrayList<Gelen> GelenListe;
	int GelenSayisi;
	ListView liste;
    String  listItemString[] ;

      
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    //	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    //	StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ekraný açýk tutar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
       
        textView=(TextView) findViewById(R.id.textView1);
         liste  = (ListView)findViewById(R.id.listView1);
 
         
         Thread t = new Thread() {

        	  @Override
        	  public void run() {
        	    try {
        	      while (!isInterrupted()) {
        	        Thread.sleep(1000);
        	        runOnUiThread(new Runnable() {
        	          @Override
        	          public void run() {
        	        	  CelsiusAsync celsiustofahr = new CelsiusAsync();    
              	         celsiustofahr.execute();
              	        
        	          }
        	        });
        	      }
        	    } catch (InterruptedException e) {
        	    }
        	  }
        	};

        	t.start();
 
        
    //    SoapObject reSoapObject = new SoapObject(NAMESPACE, METHOD_NAME);
//Egerki sorgu çekecekseniz o zaman reSoapObject.addProperty(name,value); þeklinde deðer eklemelisiniz. O da SOAP içinde belirtiliyor.
     //   SoapSerializationEnvelope soaSerializationEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
      //  reSoapObject.getProperty("dbEV");
       /*reSoapObject.getPropertyAsString("evID");
       reSoapObject.getPropertyAsString("evIL");
       reSoapObject.getPropertyAsString("evEmlakTip");
       reSoapObject.getPropertyAsString("evAlan");
       reSoapObject.getPropertyAsString("evOdaSayisi");
       reSoapObject.getPropertyAsString("evBinaYasi");
       reSoapObject.getPropertyAsString("evBulKat");
       reSoapObject.getPropertyAsString("evFiyat");
       reSoapObject.getPropertyAsString("evAciklama");
       */
   
 /*       
        soaSerializationEnvelope.dotNet = true;
        soaSerializationEnvelope.setOutputSoapObject(reSoapObject);
        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
     
        int evID =0,evAlan=0,evOdaSayisi=0,evBinaYasi=0,evBulKat=0;
        float evFiyat =0;
        String evIL=null,evEmlakTip=null,evAciklama=null;
        try {
        	httpTransportSE.call(SOAP_ACTION, soaSerializationEnvelope);
//Root elemente eriþiyoruz.
        	
SoapPrimitive soapPrimitive=(SoapPrimitive)soaSerializationEnvelope.getResponse();
            
            System.out.println(soapPrimitive.toString());
        	SoapObject soapObjectResultRoot=(SoapObject) soaSerializationEnvelope.bodyIn;
        	Log.e(">>>>>>>>>>>>>>>>>>", "Root elemtn body: "+soapObjectResultRoot.toString());
//Root elementin altýndaki eriþeceðimiz metodun ..Result ile biten deðerine eriþiyoruz. Mesela METHOD_NAME = "GetGeoIPContext";
//Aþaðýdaki metod ve parametre deðerleri bulinkte açýklýyor zaten: http://www.webservicex.net/geoipservice.asmx?op=GetGeoIPContext
        	SoapObject soapObjectGetProperty=(SoapObject) soapObjectResultRoot.getProperty("viewDBResult");
        	evID=Integer.parseInt(soapObjectGetProperty.getProperty("evID").toString());
        	evIL=soapObjectGetProperty.getProperty("evIL").toString();
        	evEmlakTip=soapObjectGetProperty.getProperty("evEmlakTip").toString();
        	evAlan=Integer.parseInt(soapObjectGetProperty.getProperty("evAlan").toString());
        	evOdaSayisi=Integer.parseInt(soapObjectGetProperty.getProperty("evOdaSayisi").toString());
        	evBinaYasi=Integer.parseInt(soapObjectGetProperty.getProperty("evBinaYasi").toString());
        	evBulKat=Integer.parseInt(soapObjectGetProperty.getProperty("evBulKat").toString());
        	evFiyat=Float.parseFloat(soapObjectGetProperty.getProperty("evFiyat").toString());
        	evAciklama=soapObjectGetProperty.getProperty("evAciklama").toString();
        } catch (IOException e) {
        e.printStackTrace();
        } catch (XmlPullParserException e) {
        e.printStackTrace();
        }
        
        textView.setText("EvID --> "+evID+" nEvIL --> "+evIL+" nEvEmlakTip --> "+evEmlakTip+" nEvAlan --> "+evAlan+" nEvOdaSayisi --> "+evOdaSayisi+" nEvBinaYasi --> "+evBinaYasi+" nEvBulKat --> "+evBulKat+" nEvFiyat --> "+evFiyat+" nEvAciklama --> "+evAciklama);
    
        */
      
    }
    // on createnin bittigi yere metod yazýldý.
	
    /*
    private void GelenListesi()
    {
         
           GelenListe = null;
           

           SoapObject request = new SoapObject(Baglanti.NAMESPACE,Baglanti.METHOD_NAME_viewDB);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(Baglanti.URL_VeritabaniService);
        androidHttpTransport.debug = true;

        try
        {
           androidHttpTransport.call(Baglanti.SOAP_ACTION_viewDB, envelope);
           SoapObject response = (SoapObject) envelope.getResponse();

           GelenListe=new ArrayList<Gelen>();

                   for(int i=0;i<response.getPropertyCount();i++)
                   {
                           Gelen ge=new Gelen();
                           SoapObject randevu = (SoapObject) response.getProperty(i);
 
                           ge.setEvID(Integer.parseInt(randevu.getProperty("evID").toString()));
                           ge.setEvIL(randevu.getProperty("evIL").toString());
                           ge.setEvEmlakTip(randevu.getProperty("evEmlakTip").toString());
                           ge.setEvAlan(Integer.parseInt(randevu.getProperty("evAlan").toString()));
                           ge.setEvOdaSayisi(Integer.parseInt(randevu.getProperty("evOdaSayisi").toString()));
                           ge.setEvBinaYasi(Integer.parseInt(randevu.getProperty("evBinaYasi").toString()));
                           ge.setEvBulKat(Integer.parseInt(randevu.getProperty("evBulKat").toString()));
                           ge.setEvFiyat(Float.parseFloat(randevu.getProperty("evFiyat").toString()));
                           ge.setEvAciklama(randevu.getProperty("evAciklama").toString());
                          

                           GelenListe.add(ge);
                }
                   GelenSayisi=Integer.parseInt(String.valueOf(response.getPropertyCount()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    */

	private class CelsiusAsync extends AsyncTask<Void, Void, Void> {

    @Override    
    protected Void doInBackground(Void... params) {
    /*	   GelenListe = null;
           SoapObject request = new SoapObject(Baglanti.NAMESPACE,Baglanti.METHOD_NAME_viewDB);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(Baglanti.URL_VeritabaniService);
        androidHttpTransport.debug = true;
        try
        {
           androidHttpTransport.call(Baglanti.SOAP_ACTION_viewDB, envelope);
      //     Log.d("dump Request: " ,androidHttpTransport.requestDump);
       //    Log.d("dump response: " ,androidHttpTransport.responseDump);
         SoapObject response = (SoapObject) envelope.getResponse();
        //   Object response =envelope.getResponse();

           GelenListe=new ArrayList<Gelen>();
           
                   for(int i=0;i<(response.getPropertyCount());i++)
                   {
                	   Gelen ge=new Gelen();
                	   SoapObject randevu = (SoapObject) response.getProperty(i);

                	   ge.setEvID(Integer.parseInt(randevu.getProperty("evID").toString()));
                	   ge.setEvIL(randevu.getProperty("evIL").toString());
                	   ge.setEvEmlakTip(randevu.getProperty("evEmlakTip").toString());
                	   ge.setEvAlan(Integer.parseInt(randevu.getProperty("evAlan").toString()));
                	   ge.setEvOdaSayisi(Integer.parseInt(randevu.getProperty("evOdaSayisi").toString()));
                	   ge.setEvBinaYasi(Integer.parseInt(randevu.getProperty("evBinaYasi").toString()));
                	   ge.setEvBulKat(Integer.parseInt(randevu.getProperty("evBulKat").toString()));
                	   ge.setEvFiyat(Float.parseFloat(randevu.getProperty("evFiyat").toString()));
                	   ge.setEvAciklama(randevu.getProperty("evAciklama").toString());
                	   
                	   ge.setResimID(Integer.parseInt(randevu.getProperty("resimID").toString()));
                	   ge.setResimYOL(randevu.getProperty("resimYOL").toString());
                	   ge.setResimEvID(Integer.parseInt(randevu.getProperty("resimEvID").toString()));
    
                           GelenListe.add(ge);
                }
                   GelenSayisi=Integer.parseInt(String.valueOf(response.getPropertyCount()));             
        }
        catch (HttpResponseException e) {
				e.printStackTrace();
			} catch (IOException e) {	
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} 
        return null;   
        */
    	GelenListe = null;
        SoapObject request = new SoapObject(Baglanti.NAMESPACE,Baglanti.METHOD_NAME_id_getir);
     SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
     envelope.dotNet = true;
     envelope.setOutputSoapObject(request);
     HttpTransportSE androidHttpTransport = new HttpTransportSE(Baglanti.URL_VeritabaniService);
     androidHttpTransport.debug = true;
     try
     {
        androidHttpTransport.call(Baglanti.SOAP_ACTION_id_getir, envelope);
   //     Log.d("dump Request: " ,androidHttpTransport.requestDump);
    //    Log.d("dump response: " ,androidHttpTransport.responseDump);
      SoapObject response = (SoapObject) envelope.getResponse();
     //   Object response =envelope.getResponse();

        GelenListe=new ArrayList<Gelen>();
        listItemString = new String[response.getPropertyCount()];
        int alinan;
        String alinan_S;
   
        
                for(int i=0;i<(response.getPropertyCount());i++)
                {
             	   Gelen ge=new Gelen();
             	   SoapObject randevu = (SoapObject) response.getProperty(i);

             	   ge.setEvID(Integer.parseInt(randevu.getProperty("evID").toString()));
             	  ge.setEvID(Integer.parseInt(randevu.getProperty("evID").toString()));
             	  alinan =(ge.getEvID());
             	 
             	  listItemString[i]  = String.valueOf(alinan);
                    GelenListe.add(ge);
             }
                GelenSayisi=Integer.parseInt(String.valueOf(response.getPropertyCount()));             
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
     
     ArrayAdapter<String> adapter  = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1 ,listItemString);

    	liste.setAdapter(adapter);
    	liste.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    int secilen_ev_id  =Integer.parseInt( listItemString[position]);
			    String secilen_ev_id_string = String.valueOf(secilen_ev_id);
			    Intent newIntent = new Intent(MainActivity.this,Listele.class);
			    newIntent.putExtra("EvID",secilen_ev_id_string);
			    startActivity(newIntent);
				
				
			}
    		
		});
      
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
