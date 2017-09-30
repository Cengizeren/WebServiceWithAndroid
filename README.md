# WebServiceWithAndroid
Sunucuda bulunan ev bilgilerini ve eve ait olan resimleri telefon üzerinden gösteren mobil bir
uygulamanın (ANDROID ya da IOS) yazılması istenmektedir. Sunucudaki bilgileri mobil
uygulama üzerinde gösterebilmek için SOAP(C#) ya da RESTful(java) web servis kullanılması
gerekmektedir. Ayrıca sunucuda veri tabanı olarak SQLite kullanılacaktır. Sunucuda SQLite veri
tabanına bilgi girişi yapılması için bir arayüze gerek yoktur. SQLite komutları kullanılarak
(INSERT,UPDATE,DELETE vb.) işlemler gerçekleştirilecektir. SQLite komutlarının öğrenilmesi
öğrenciye aittir. Aşağıdaki tabloda SQLite için gerekli olan veri tabanı ve tablo isimleri bir
örnekle gösterilmiştir.

Veri tabanı adı : dbEV
Tablo Adı : tblEV Tablo Adı : tblRESIM
evID(INTEGER,PK),
evIL(TEXT),
evEmlakTip(TEXT),
evAlan(INTEGER),
evOdaSayisi(INTEGER),
evBinaYasi(INTEGER),
evBulKat(INTEGER),
evFiyat(REAL),
evAciklama(TEXT(MAX-200))
resimID(INTEGER,PK),
resimYol(TEXT),
resimEvID(INTEGER,FK{tblEV])


Örnek Kayıt:
evID=1,
evIL= Kocaeli,
evEmlakTip = Satılık,
evAlan = 110,
evOdaSayisi = 3+1,
evBinaYasi = 12
evBulKat = 3. Kat
evFiyat = 135.000 TL
evAciklama= “Ev Yatırım için Uygun.”
resimID=1
resimYol=”C:\User\Kou\1.jpg”
resimEvID=1
-------------------------
resimID=2
resimYol=”C:\User\Kou\2.jpg”
resimEvID=1
-------------------------
resimID=3
resimYol=”C:\User\Kou\3.jpg”
resimEvID=1

Mobil uygulama web servisi kullanarak sunucudaki veri tabanında bulunan evin bilgilerini liste
halinde göstermelidir. Eğer evlerden birine tıklanılırsa ev ile ilgili bilgiler ve resimler kullanıcıya
gösterilmesi gerekmektedir. Sunucudan veri tabanına yeni bir ev bilgisi eklendiğinde telefon
üzerindeki uygulama bunu farkedip ev listesini güncellemelidir. Bu işlemler yapılırken telefona
kesme atılabilir (telefon çağrısının gelmesi vb.). Program bu kesmelere rağmen çalışmasına
devam etmelidir.
