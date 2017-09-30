using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Data;
using System.Data.SQLite;

namespace WebApplication4
{
	/// <summary>
	/// Summary description for WebService1
	/// </summary>
	[WebService(Namespace = "http://tempuri.org/")]
	[WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
	[System.ComponentModel.ToolboxItem(false)]
	// To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
	// [System.Web.Script.Services.ScriptService]
	public class WebService1 : System.Web.Services.WebService
	{
		public class Ev
		{
			public int evID;
			public String evIL;
			public String evEmlakTip;
			public int evAlan;
			public int evOdaSayisi;
			public int evBinaYasi;
			public int evBulKat;
			public double evFiyat;
			public String evAciklama;

			public int resimID;
			public string resimYOL;
			public int resimEvID;
			public int getEvID()
			{
				return evID;
			}
			public void setEvID(int evID)
			{
				this.evID = evID;
			}
			public String getEvIL()
			{
				return evIL;
			}
			public void setEvIL(String evIL)
			{
				this.evIL = evIL;
			}
			public String getEvEmlakTip()
			{
				return evEmlakTip;
			}
			public void setEvEmlakTip(String evEmlakTip)
			{
				this.evEmlakTip = evEmlakTip;
			}
			public int getEvAlan()
			{
				return evAlan;
			}
			public void setEvAlan(int evAlan)
			{
				this.evAlan = evAlan;
			}
			public int getEvOdaSayisi()
			{
				return evOdaSayisi;
			}
			public void setEvOdaSayisi(int evOdaSayisi)
			{
				this.evOdaSayisi = evOdaSayisi;
			}
			public int getEvBinaYasi()
			{
				return evBinaYasi;
			}
			public void setEvBinaYasi(int evBinaYasi)
			{
				this.evBinaYasi = evBinaYasi;
			}
			public int getEvBulKat()
			{
				return evBulKat;
			}
			public void setEvBulKat(int evBulKat)
			{
				this.evBulKat = evBulKat;
			}
			public double getEvFiyat()
			{
				return evFiyat;
			}
			public void setEvFiyat(double evFiyat)
			{
				this.evFiyat = evFiyat;
			}
			public String getEvAciklama()
			{
				return evAciklama;
			}
			public void setEvAciklama(String evAciklama)
			{
				this.evAciklama = evAciklama;
			}
		}

		// resim sql cumlesinde foreign key kısmını kod ile bak
		// update komutuna bak
		[WebMethod]
		 public List<Ev> id_getir()
		{

			SQLiteConnection conn = new SQLiteConnection(@"Data Source=C:\Users\Cengo105\Desktop\dbEV.sqlite ;Version=3;");
			List<Ev> dbList = new List<Ev>();

			conn.Open();
			SQLiteCommand cmd = conn.CreateCommand();
			cmd.CommandText = "select evID from tblEV";
			cmd.ExecuteNonQuery();
			SQLiteDataReader dr = cmd.ExecuteReader();
			while (dr.Read())
			{
				Ev ev = new Ev();
				ev.evID = (Convert.ToInt32(dr["evID"]));
				dbList.Add(ev);
			}
			conn.Close();
			return dbList;
		}

		[WebMethod]
		public List<Ev> ID_yeGore_Getir(int id)
		{

			SQLiteConnection conn = new SQLiteConnection(@"Data Source=C:\Users\Cengo105\Desktop\dbEV.sqlite ;Version=3;");
			List<Ev> dbList = new List<Ev>();

			conn.Open();
			SQLiteCommand cmd = conn.CreateCommand();
			cmd.CommandText = "select * from tblEV,tblRESIM where evID = ? AND evID = resimEvID";
			cmd.Parameters.AddWithValue("evID", id);
			cmd.ExecuteNonQuery();
			SQLiteDataReader dr = cmd.ExecuteReader();
			while (dr.Read())
			{
				Ev ev = new Ev();
				ev.evID = (Convert.ToInt32(dr["evID"]));
				ev.evIL = (dr["evIL"].ToString());
				ev.evEmlakTip = (dr["evEmlakTip"].ToString());
				ev.evAlan = (Convert.ToInt32(dr["evAlan"]));
				ev.evOdaSayisi = (Convert.ToInt32(dr["evOdaSayisi"]));
				ev.evBinaYasi = (Convert.ToInt32(dr["evBinaYasi"]));
				ev.evBulKat = (Convert.ToInt32(dr["evBulKat"]));
				ev.evFiyat = (Convert.ToDouble(dr["evFiyat"]));
				ev.evAciklama = (dr["evAciklama"].ToString());
				ev.resimID = Convert.ToInt32(dr["resimID"].ToString());
				ev.resimYOL = dr["resimYOL"].ToString();
				ev.resimEvID = Convert.ToInt32(dr["resimEvID"].ToString());
				dbList.Add(ev);

			}
			conn.Close();
			return dbList;
		}


		/*public DataSet get()
		{
			SQLiteConnection conn = new SQLiteConnection(@"Data Source=C:\Users\Cengo105\Desktop\dbEV.sqlite ;Version=3;");
			conn.Open();
			SQLiteDataAdapter da = new SQLiteDataAdapter("select * from tblEV,tblRESIM",conn);
			DataSet ds = new DataSet();
			da.Fill(ds);
			return ds;
		}
		*/
		/*public DataTable get()
		{
			SQLiteConnection conn = new SQLiteConnection(@"Data Source=C:\Users\Cengo105\Desktop\dbEV.sqlite ;Version=3;");
			conn.Open();
			SQLiteDataAdapter da = new SQLiteDataAdapter("select * from tblEV,tblRESIM", conn);
			DataTable dt = new DataTable();
			dt.TableName = "tblEV";
			da.Fill(dt);
			return dt;
		}
		*/


		[WebMethod]
		public List<Ev> viewDB()
		{
			
			SQLiteConnection conn = new SQLiteConnection(@"Data Source=C:\Users\Cengo105\Desktop\dbEV.sqlite ;Version=3;");
			List<Ev> dbList = new List<Ev>();
			
			conn.Open();
			SQLiteCommand cmd = conn.CreateCommand();
			cmd.CommandText = "select * from tblEV,tblRESIM";
			cmd.ExecuteNonQuery();
			SQLiteDataReader dr = cmd.ExecuteReader();
			while (dr.Read())
			{
				Ev ev = new Ev();
				/*
				ev.setEvID(Convert.ToInt32(dr["evID"]));
				ev.setEvIL(dr["evIL"].ToString());
				ev.setEvEmlakTip(dr["evEmlakTip"].ToString());
				ev.setEvAlan(Convert.ToInt32(dr["evAlan"]));
				ev.setEvOdaSayisi(Convert.ToInt32(dr["evOdaSayisi"]));
				ev.setEvBinaYasi(Convert.ToInt32(dr["evBinaYasi"]));
				ev.setEvBulKat(Convert.ToInt32(dr["evBulKat"]));
				ev.setEvFiyat(Convert.ToDouble(dr["evFiyat"]));
				ev.setEvAciklama(dr["evAciklama"].ToString());
				*/
				ev.evID=(Convert.ToInt32(dr["evID"]));
				ev.evIL =(dr["evIL"].ToString());
				ev.evEmlakTip =(dr["evEmlakTip"].ToString());
				ev.evAlan=(Convert.ToInt32(dr["evAlan"]));
				ev.evOdaSayisi =(Convert.ToInt32(dr["evOdaSayisi"]));
				ev.evBinaYasi =(Convert.ToInt32(dr["evBinaYasi"]));
				ev.evBulKat =(Convert.ToInt32(dr["evBulKat"]));
				ev.evFiyat =(Convert.ToDouble(dr["evFiyat"]));
				ev.evAciklama =(dr["evAciklama"].ToString());
				ev.resimID = Convert.ToInt32(dr["resimID"].ToString());
				ev.resimYOL = dr["resimYOL"].ToString();
				ev.resimEvID = Convert.ToInt32(dr["resimEvID"].ToString());
				dbList.Add(ev);
			}

				/*dbList.Add(dr["evID"].ToString();
				dbList.Add(dr["evIL"].ToString());
				dbList.Add(dr["evEmlakTip"].ToString());
				dbList.Add(dr["evAlan"].ToString());
				dbList.Add(dr["evOdaSayisi"].ToString());
				dbList.Add(dr["evBinaYasi"].ToString());
				dbList.Add(dr["evBulKat"].ToString());
				dbList.Add(dr["evFiyat"].ToString());
				dbList.Add(dr["evAciklama"].ToString());
				*/
				/*
			}
	
	/*	SQLiteCommand cmd1 = conn.CreateCommand();
		cmd1.CommandText = "select * from tblRESIM";
		cmd1.ExecuteNonQuery();
		SQLiteDataReader dr1 = cmd1.ExecuteReader();
		while (dr1.Read())
		{
			dbList.Add(dr1["resimID"].ToString());
			dbList.Add(dr1["resimYOL"].ToString());
			dbList.Add(dr1["resimEvID"].ToString());
		}

		*/
	
	conn.Close();

	return dbList;
}

/*

	public List<string> viewDB()
	{
		
		SQLiteConnection conn = new SQLiteConnection(@"Data Source=C:\Users\Cengo105\Desktop\dbEV.sqlite ;Version=3;");
		List<string> dbList = new List<string>();

		conn.Open();
		SQLiteCommand cmd = conn.CreateCommand();
		cmd.CommandText = "select * from tblEV,tblRESIM";
		cmd.ExecuteNonQuery();
		SQLiteDataReader dr = cmd.ExecuteReader();
		while (dr.Read())
		{
	
			dbList.Add(dr["evID"].ToString());
			dbList.Add(dr["evIL"].ToString());
			dbList.Add(dr["evEmlakTip"].ToString());
			dbList.Add(dr["evAlan"].ToString());
			dbList.Add(dr["evOdaSayisi"].ToString());
			dbList.Add(dr["evBinaYasi"].ToString());
			dbList.Add(dr["evBulKat"].ToString());
			dbList.Add(dr["evFiyat"].ToString());
			dbList.Add(dr["evAciklama"].ToString());
				dbList.Add(dr["resimID"].ToString());
				dbList.Add(dr["resimYOL"].ToString());
				dbList.Add(dr["resimEvID"].ToString());

			}
		
		conn.Close();

		return dbList;
	}
	*/

	/*
	[WebMethod]
	public void InsertDB(int evID,string evIL,string evEmlakTip , int evAlan,int evOdaSayisi,int evBinaYasi,int evBulKat,float evFiyat,string evAciklama,int resimID,string resimYOL,int resimEvID)
	{

		string dbName = "dbEV.sqlite";
		//	SQLiteConnection.CreateFile(dbName);
		//	Yoksa oluşturacaktır.
		SQLiteConnection conn = new SQLiteConnection(@"Data Source=C:\Users\Cengo105\Desktop\dbEV.sqlite ;Version=3;");
		//	Version3, indirdiğimiz SQLite'in  sürümünü temsil etmektedir.
		conn.Open();
		/*	string sql = "create table tblEV (evID INTEGER PRIMARY KEY, evIL TEXT,evEmlakTip TEXT,evAlan INTEGER,evOdaSayisi INTEGER,evBinaYasi INTEGER,evBulKat INTEGER,evFiyat REAL,evAciklama TEXT(MAX-200))";
			//SQL tablomuzu id'nin int değer alacak ve benzersiz bir key olması ve auto increment olması durumunda; id INTEGER PRIMARY KEY kullanıyoruz.
			SQLiteCommand command = new SQLiteCommand(sql, conn);
			command.ExecuteNonQuery();
			*/
		//	string insertEv = "insert into tblEV (evID,evIL,evEmlakTip,evAlan,evOdaSayisi,evBinaYasi,evBulKat,evFiyat,evAciklama) values (1,'Kocaeli','Satilik',110,3+1,12,3,135000,'Ev Yatırım İçin Uygun')";
		/*
					string varmiEv = "Select Count(*) FROM tblEV where evID=@evID and evIL=@evIL and evEmlakTip=@evEmlakTip and evAlan=@evAlan and evOdaSayisi=@evOdaSayisi and evBinaYasi =@evBinaYasi and evBulKat=@evBulKat and evFiyat=@evFiyat and evAciklama=@evAciklama";
					SQLiteCommand command = new SQLiteCommand(varmiEv, conn);
					command.Parameters.AddWithValue("@evID", evID);
					command.Parameters.AddWithValue("@evIL", evIL);
					command.Parameters.AddWithValue("@evEmlakTip", evEmlakTip);
					command.Parameters.AddWithValue("@evAlan", evAlan);
					command.Parameters.AddWithValue("@evOdaSayisi", evOdaSayisi);
					command.Parameters.AddWithValue("@evBinaYasi", evBinaYasi);
					command.Parameters.AddWithValue("@evBulKat", evBulKat);
					command.Parameters.AddWithValue("@evFiyat", evFiyat);
					command.Parameters.AddWithValue("@evAciklama", evAciklama);
					int sayiEv = Convert.ToInt32(command.ExecuteScalar());

					string varmiResim = "Select Count(*) FROM tblRESIM where resimID=@resimID and resimYOL=@resimYOL and resimEvID=@resimEvID";
					SQLiteCommand command2 = new SQLiteCommand(varmiResim, conn);
					command2.Parameters.AddWithValue("@resimID", resimID);
					command2.Parameters.AddWithValue("@resimYOL", resimYOL);
					command2.Parameters.AddWithValue("@resimEvID", resimEvID);

					int sayiResim = Convert.ToInt32(command2.ExecuteScalar());

					conn.Close();
					// Eğer veri daha once varsa mesaj goster 
					if (sayiEv != 0 || sayiResim !=0)
					{

					}
					else
					{
						conn.Open();
						string insertEv = "insert into tblEV (evID,evIL,evEmlakTip,evAlan,evOdaSayisi,evBinaYasi,evBulKat,evFiyat,evAciklama) values (@evID,@evIL,@evEmlakTip,@evAlan,@evOdaSayisi,@evBinaYasi,@evBulKat,@evFiyat,@evAciklama)";


						SQLiteCommand command1 = new SQLiteCommand(insertEv, conn);
						command1.Parameters.AddWithValue("@evID", evID);
						command1.Parameters.AddWithValue("@evIL", evIL);
						command1.Parameters.AddWithValue("@evEmlakTip", evEmlakTip);
						command1.Parameters.AddWithValue("@evAlan", evAlan);
						command1.Parameters.AddWithValue("@evOdaSayisi", evOdaSayisi);
						command1.Parameters.AddWithValue("@evBinaYasi", evBinaYasi);
						command1.Parameters.AddWithValue("@evBulKat", evBulKat);
						command1.Parameters.AddWithValue("@evFiyat", evFiyat);
						command1.Parameters.AddWithValue("@evAciklama", evAciklama);
						command1.ExecuteNonQuery();


					//	string insertResim = @"insert into tblRESIM (resimID,resimYOL,resimEvID) values (1,'C:\Users\Cengo105\Desktop\ev1.jpg',1)";
						string insertResim = @"insert into tblRESIM (resimID,resimYOL,resimEvID) values (@resimID,@resimYOL,@resimEvID)";
						SQLiteCommand command3 = new SQLiteCommand(insertResim, conn);
						command3.Parameters.AddWithValue("@resimID", resimID);
						command3.Parameters.AddWithValue("@resimYOL", resimYOL);
						command3.Parameters.AddWithValue("@resimEvID", resimEvID);
						command3.ExecuteNonQuery();
						conn.Close();

					}


				}
				*/

		/*
		[WebMethod]
		public void deleteDB(int evID,int resimID)
		{
			SQLiteConnection conn = new SQLiteConnection(@"Data Source=C:\Users\Cengo105\Desktop\dbEV.sqlite ;Version=3;");
			conn.Open();

			string deleteEv = "Delete from tblEV where evID = @evID";
			SQLiteCommand command = new SQLiteCommand(deleteEv, conn);
			command.Parameters.AddWithValue("@evID", evID);
			command.ExecuteNonQuery();

			string deleteResim = "Delete from tblRESIM where resimID = @resimID";
			SQLiteCommand command1 = new SQLiteCommand(deleteResim, conn);
			command1.Parameters.AddWithValue("@resimID", resimID);
			command1.ExecuteNonQuery();

			conn.Close();
		}
		*/

		/*
			[WebMethod]
			public List<string> updateDB(int evID, string evIL, string evEmlakTip, int evAlan, int evOdaSayisi, int evBinaYasi, int evBulKat, float evFiyat, string evAciklama, int resimID, string resimYOL, int resimEvID)
			{
				string dbName = "dbEV.sqlite";
				//	Version3, indirdiğimiz SQLite'in  sürümünü temsil etmektedir.
				SQLiteConnection conn = new SQLiteConnection(@"Data Source=C:\Users\Cengo105\Desktop\dbEV.sqlite ;Version=3;");
				conn.Open();
				string varmiEv = "Select Count(*) FROM tblEV where evID=@evID and evIL=@evIL and evEmlakTip=@evEmlakTip and evAlan=@evAlan and evOdaSayisi=@evOdaSayisi and evBinaYasi =@evBinaYasi and evBulKat=@evBulKat and evFiyat=@evFiyat and evAciklama=@evAciklama";
				SQLiteCommand command = new SQLiteCommand(varmiEv, conn);
				command.Parameters.AddWithValue("@evID", evID);
				command.Parameters.AddWithValue("@evIL", evIL);
				command.Parameters.AddWithValue("@evEmlakTip", evEmlakTip);
				command.Parameters.AddWithValue("@evAlan", evAlan);
				command.Parameters.AddWithValue("@evOdaSayisi", evOdaSayisi);
				command.Parameters.AddWithValue("@evBinaYasi", evBinaYasi);
				command.Parameters.AddWithValue("@evBulKat", evBulKat);
				command.Parameters.AddWithValue("@evFiyat", evFiyat);
				command.Parameters.AddWithValue("@evAciklama", evAciklama);
				int sayiEv = Convert.ToInt32(command.ExecuteScalar());

				string varmiResim = "Select Count(*) FROM tblRESIM where resimID=@resimID and resimYOL=@resimYOL and resimEvID=@resimEvID";
				SQLiteCommand command2 = new SQLiteCommand(varmiResim, conn);
				command2.Parameters.AddWithValue("@resimID", resimID);
				command2.Parameters.AddWithValue("@resimYOL", resimYOL);
				command2.Parameters.AddWithValue("@resimEvID", resimEvID);

				int sayiResim = Convert.ToInt32(command2.ExecuteScalar());

				conn.Close();
				// Eğer veri daha once varsa mesaj goster 
				if (sayiEv != 0 || sayiResim != 0)
				{

				}
				else
				{
					conn.Open();

					string updateEv = "update tblEV set evIL=@evIL,evEmlakTip=@evEmlakTip,evAlan=@evAlan,evOdaSayisi=@evOdaSayisi,evBinaYasi=@evBinaYasi,evBulKat=@evBulKat,evFiyat=@evFiyat,evAciklama=@evAciklama where evID=@evID";
					SQLiteCommand command1 = new SQLiteCommand(updateEv, conn);
					command1.Parameters.AddWithValue("@evID", evID);
					command1.Parameters.AddWithValue("@evIL", evIL);
					command1.Parameters.AddWithValue("@evEmlakTip", evEmlakTip);
					command1.Parameters.AddWithValue("@evAlan", evAlan);
					command1.Parameters.AddWithValue("@evOdaSayisi", evOdaSayisi);
					command1.Parameters.AddWithValue("@evBinaYasi", evBinaYasi);
					command1.Parameters.AddWithValue("@evBulKat", evBulKat);
					command1.Parameters.AddWithValue("@evFiyat", evFiyat);
					command1.Parameters.AddWithValue("@evAciklama", evAciklama);
					command1.ExecuteNonQuery();


					//	string insertResim = @"insert into tblRESIM (resimID,resimYOL,resimEvID) values (1,'C:\Users\Cengo105\Desktop\ev1.jpg',1)";
					string updateResim = @"update tblRESIM set resimYOL=@resimYOL,resimEvID=@resimEvID where resimID=@resimID";
					SQLiteCommand command3 = new SQLiteCommand(updateResim, conn);
					command3.Parameters.AddWithValue("@resimID", resimID);
					command3.Parameters.AddWithValue("@resimYOL", resimYOL);
					command3.Parameters.AddWithValue("@resimEvID", resimEvID);
					command3.ExecuteNonQuery();
					conn.Close();

				}

				List<string> dbList = new List<string>();

				conn.Open();
				SQLiteCommand cmd = conn.CreateCommand();
				cmd.CommandText = "select * from tblEV";
				cmd.ExecuteNonQuery();
				SQLiteDataReader dr = cmd.ExecuteReader();
				while (dr.Read())
				{
					dbList.Add(dr["evID"].ToString());
					dbList.Add(dr["evIL"].ToString());
					dbList.Add(dr["evEmlakTip"].ToString());
					dbList.Add(dr["evAlan"].ToString());
					dbList.Add(dr["evOdaSayisi"].ToString());
					dbList.Add(dr["evBinaYasi"].ToString());
					dbList.Add(dr["evBulKat"].ToString());
					dbList.Add(dr["evFiyat"].ToString());
					dbList.Add(dr["evAciklama"].ToString());
				}

				SQLiteCommand cmd1 = conn.CreateCommand();
				cmd1.CommandText = "select * from tblRESIM";
				cmd1.ExecuteNonQuery();
				SQLiteDataReader dr1 = cmd1.ExecuteReader();
				while (dr1.Read())
				{
					dbList.Add(dr1["resimID"].ToString());
					dbList.Add(dr1["resimYOL"].ToString());
					dbList.Add(dr1["resimEvID"].ToString());
				}

				conn.Close();

				return dbList;
			}
			*/
	}
}
