
import pandas as pd


def get_graph():
    graph_of_cities = {
        'ADANA': ['HATAY', 'OSMANİYE', 'KAHRAMANMARAŞ', 'KAYSERİ', 'NİĞDE', 'MERSİN'],
        'ADIYAMAN': ['ŞANLIURFA', 'DİYARBAKIR', 'MALATYA', 'KAHRAMANMARAŞ', 'GAZİANTEP'],
        'AFYONKARAHİSAR': ['ISPARTA', 'KONYA', 'ESKİŞEHİR', 'KÜTAHYA', 'UŞAK', 'DENİZLİ', 'BURDUR'],
        'AĞRI': ['VAN', 'IĞDIR', 'KARS', 'ERZURUM', 'MUŞ', 'BİTLİS'],
        'AMASYA': ['YOZGAT', 'TOKAT', 'SAMSUN', 'ÇORUM'],
        'ANKARA': ['KONYA', 'AKSARAY', 'KIRŞEHİR', 'KIRIKKALE', 'ÇANKIRI', 'BOLU', 'ESKİŞEHİR'],
        'ANTALYA': ['MERSİN', 'KARAMAN', 'KONYA', 'ISPARTA', 'BURDUR', 'MUĞLA'],
        'ARTVİN': ['RİZE', 'ERZURUM', 'ARDAHAN'],
        'AYDIN': ['MUĞLA', 'DENİZLİ', 'MANİSA', 'İZMİR'],
        'BALIKESİR': ['İZMİR', 'MANİSA', 'KÜTAHYA', 'BURSA', 'ÇANAKKALE'],
        'BİLECİK': ['KÜTAHYA', 'ESKİŞEHİR', 'BOLU', 'SAKARYA(ADAPAZARI)', 'BURSA'],
        'BİNGÖL': ['DİYARBAKIR', 'MUŞ', 'ERZURUM', 'ERZİNCAN', 'TUNCELİ', 'ELAZIĞ'],
        'BİTLİS': ['SİİRT', 'VAN', 'AĞRI', 'MUŞ', 'BATMAN'],
        'BOLU': ['ESKİŞEHİR', 'ANKARA', 'ÇANKIRI', 'ZONGULDAK', 'DÜZCE', 'SAKARYA(ADAPAZARI)', 'BİLECİK'],
        'BURDUR': ['MUĞLA', 'ANTALYA', 'ISPARTA', 'AFYONKARAHİSAR', 'DENİZLİ'],
        'BURSA': ['BALIKESİR', 'KÜTAHYA', 'BİLECİK', 'SAKARYA(ADAPAZARI)', 'KOCAELİ(İZMİT)', 'YALOVA'],
        'ÇANAKKALE': ['BALIKESİR', 'TEKİRDAĞ', 'EDİRNE'],
        'ÇANKIRI': ['ANKARA', 'KIRIKKALE', 'ÇORUM', 'KASTAMONU', 'ZONGULDAK', 'BOLU', 'KARABÜK'],
        'ÇORUM': ['YOZGAT', 'AMASYA', 'SAMSUN', 'SİNOP', 'KASTAMONU', 'ÇANKIRI', 'KIRIKKALE'],
        'DENİZLİ': ['MUĞLA', 'BURDUR', 'AFYONKARAHİSAR', 'UŞAK', 'MANİSA', 'AYDIN'],
        'DİYARBAKIR': ['ŞANLIURFA', 'MARDİN', 'BATMAN', 'MUŞ', 'BİNGÖL', 'ELAZIĞ', 'MALATYA', 'ADIYAMAN'],
        'EDİRNE': ['ÇANAKKALE', 'TEKİRDAĞ', 'KIRIKKALE'],
        'ELAZIĞ': ['DİYARBAKIR', 'BİNGÖL', 'TUNCELİ', 'ERZİNCAN', 'MALATYA'],
        'ERZİNCAN': ['ELAZIĞ', 'TUNCELİ', 'BİNGÖL', 'ERZURUM', 'BAYBURT', 'GÜMÜŞHANE', 'GİRESUN', 'SİVAS', 'MALATYA'],
        'ERZURUM': ['BİNGÖL', 'MUŞ', 'AĞRI', 'KARS', 'ARDAHAN', 'ARTVİN', 'RİZE', 'TRABZON', 'BAYBURT', 'ERZİNCAN'],
        'ESKİŞEHİR': ['AFYONKARAHİSAR', 'KONYA', 'ANKARA', 'BOLU', 'BİLECİK', 'KÜTAHYA'],
        'GAZİANTEP': ['KİLİS', 'ŞANLIURFA', 'ADIYAMAN', 'KAHRAMANMARAŞ', 'OSMANİYE', 'HATAY'],
        'GİRESUN': ['GÜMÜŞHANE', 'TRABZON', 'ERZİNCAN', 'SİVAS', 'ORDU'],
        'GÜMÜŞHANE': ['ERZİNCAN', 'BAYBURT', 'TRABZON', 'GİRESUN'],
        'HAKKARİ': ['VAN', 'ŞIRNAK'],
        'HATAY': ['KİLİS', 'GAZİANTEP', 'OSMANİYE', 'ADANA'],
        'ISPARTA': ['ANTALYA', 'KONYA', 'AFYONKARAHİSAR', 'BURDUR'],
        'MERSİN': ['ADANA', 'NİĞDE', 'KONYA', 'KARAMAN', 'ANTALYA'],
        'İSTANBUL': ['KOCAELİ(İZMİT)', 'TEKİRDAĞ', 'KIRKLARELİ'],
        'İZMİR': ['AYDIN', 'MANİSA', 'BALIKESİR'],
        'KARS': ['AĞRI', 'IĞDIR', 'ARDAHAN', 'ERZURUM'],
        'KASTAMONU': ['ÇORUM', 'SİNOP', 'ÇANKIRI', 'BARTIN', 'KARABÜK'],
        'KAYSERİ': ['ADANA', 'KAHRAMANMARAŞ', 'SİVAS', 'YOZGAT', 'NEVŞEHİR', 'NİĞDE'],
        'KIRKLARELİ': ['EDİRNE', 'TEKİRDAĞ', 'İSTANBUL'],
        'KIRŞEHİR': ['NEVŞEHİR', 'YOZGAT', 'KIRIKKALE', 'ANKARA', 'AKSARAY'],
        'KOCAELİ(İZMİT)': ['YALOVA', 'İSTANBUL', 'BURSA', 'BİLECİK', 'SAKARYA(ADAPAZARI)'],
        'KONYA': ['ANTALYA', 'KARAMAN', 'MERSİN', 'NİĞDE', 'AKSARAY', 'ANKARA', 'ESKİŞEHİR', 'AFYONKARAHİSAR',
                  'ISPARTA'],
        'KÜTAHYA': ['MANİSA', 'UŞAK', 'AFYONKARAHİSAR', 'ESKİŞEHİR', 'BİLECİK', 'BURSA', 'BALIKESİR'],
        'MALATYA': ['KAHRAMANMARAŞ', 'ADIYAMAN', 'DİYARBAKIR', 'ELAZIĞ', 'ERZİNCAN', 'SİVAS'],
        'MANİSA': ['İZMİR', 'AYDIN', 'DENİZLİ', 'UŞAK', 'KÜTAHYA', 'BALIKESİR'],
        'KAHRAMANMARAŞ': ['GAZİANTEP', 'ADIYAMAN', 'MALATYA', 'SİVAS', 'KAYSERİ', 'ADANA', 'OSMANİYE'],
        'MARDİN': ['ŞANLIURFA', 'DİYARBAKIR', 'BATMAN', 'SİİRT', 'ŞIRNAK'],
        'MUĞLA': ['ANTALYA', 'BURDUR', 'DENİZLİ', 'AYDIN'],
        'MUŞ': ['DİYARBAKIR', 'BATMAN', 'BİTLİS', 'AĞRI', 'ERZURUM', 'BİNGÖL'],
        'NEVŞEHİR': ['NİĞDE', 'KAYSERİ', 'YOZGAT', 'KIRŞEHİR', 'AKSARAY'],
        'NİĞDE': ['NEVŞEHİR', 'KAYSERİ', 'ADANA', 'MERSİN', 'KONYA', 'AKSARAY'],
        'ORDU': ['SAMSUN', 'TOKAT', 'SİVAS', 'GİRESUN'],
        'RİZE': ['ARTVİN', 'ERZURUM', 'BAYBURT', 'TRABZON'],
        'SAKARYA(ADAPAZARI)': ['DÜZCE', 'BOLU', 'BİLECİK', 'BURSA', 'KOCAELİ(İZMİT)'],
        'SAMSUN': ['ORDU', 'TOKAT', 'AMASYA', 'ÇORUM', 'SİNOP'],
        'SİİRT': ['VAN', 'BİTLİS', 'BATMAN', 'MARDİN', 'ŞIRNAK'],
        'SİNOP': ['SAMSUN', 'ÇORUM', 'KASTAMONU'],
        'SİVAS': ['KAYSERİ', 'KAHRAMANMARAŞ', 'MALATYA', 'ERZİNCAN', 'GİRESUN', 'ORDU', 'TOKAT', 'YOZGAT'],
        'TEKİRDAĞ': ['İSTANBUL', 'KIRKLARELİ', 'EDİRNE', 'ÇANAKKALE'],
        'TOKAT': ['SİVAS', 'ORDU', 'SAMSUN', 'AMASYA', 'YOZGAT'],
        'TRABZON': ['RİZE', 'BAYBURT', 'GÜMÜŞHANE', 'GİRESUN'],
        'TUNCELİ': ['ELAZIĞ', 'BİNGÖL', 'ERZİNCAN'],
        'ŞANLIURFA': ['GAZİANTEP', 'ADIYAMAN', 'DİYARBAKIR', 'MARDİN'],
        'UŞAK': ['MANİSA', 'DENİZLİ', 'AFYONKARAHİSAR', 'KÜTAHYA'],
        'VAN': ['HAKKARİ', 'ŞIRNAK', 'SİİRT', 'BİTLİS', 'AĞRI'],
        'YOZGAT': ['KAYSERİ', 'SİVAS', 'TOKAT', 'AMASYA', 'ÇORUM', 'KIRIKKALE', 'KIRŞEHİR', 'NEVŞEHİR'],
        'ZONGULDAK': ['BARTIN', 'ÇANKIRI', 'BOLU', 'DÜZCE', 'KARABÜK'],
        'AKSARAY': ['NİĞDE', 'NEVŞEHİR', 'KIRŞEHİR', 'ANKARA', 'KONYA'],
        'BAYBURT': ['ERZİNCAN', 'ERZURUM', 'RİZE', 'TRABZON', 'GÜMÜŞHANE'],
        'KARAMAN': ['MERSİN', 'KONYA', 'ANTALYA'],
        'KIRIKKALE': ['KIRŞEHİR', 'YOZGAT', 'ÇORUM', 'ÇANKIRI', 'ANKARA'],
        'BATMAN': ['MARDİN', 'SİİRT', 'BİTLİS', 'MUŞ', 'DİYARBAKIR'],
        'ŞIRNAK': ['MARDİN', 'SİİRT', 'VAN', 'HAKKARİ'],
        'BARTIN': ['KASTAMONU', 'ZONGULDAK', 'KARABÜK'],
        'ARDAHAN': ['KARS', 'ERZURUM', 'ARTVİN'],
        'IĞDIR': ['AĞRI', 'KARS'],
        'YALOVA': ['KOCAELİ(İZMİT)', 'BURSA'],
        'KARABÜK': ['ZONGULDAK', 'BARTIN', 'KASTAMONU', 'ÇANKIRI'],
        'KİLİS': ['GAZİANTEP', 'HATAY'],
        'OSMANİYE': ['GAZİANTEP', 'KAHRAMANMARAŞ', 'ADANA', 'HATAY'],
        'DÜZCE': ['ZONGULDAK', 'BOLU', 'SAKARYA(ADAPAZARI)']

    }

    return graph_of_cities


def get_distances():
    return pd.read_csv('iller_mesafe.csv')

