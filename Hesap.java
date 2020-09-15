

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Hesap extends JFrame {

    JComboBox<Integer> [] krediler;
    JLabel [] dersler;
    JComboBox<String> []  notlar;
    boolean gpaHesapla;
    int dersSayisi;
    int frameBoy;
    int frameEn;
    int aralik=20;
    int fieldBoy=30;
    int fieldEn=30;
    int baslangicy=40;
    int x=200;
    int dersY=40;
    String ders="dersler";
    Integer [] items ={1,2,3,4,5,6,7,8,9,10};
    String [] notItems ={"AA","BA","BB","CB","CC","DC","DD","FD","FF"};
    JTextField gpaGiris;
    JTextField krediGiris;
    
    public Hesap() throws HeadlessException {
    }
    
    public Hesap(int dersSayisi, boolean gpaHesapla) throws HeadlessException {
        
        this.gpaHesapla = gpaHesapla;
        this.dersSayisi = dersSayisi;
        
        krediler=new JComboBox[dersSayisi];
        notlar=new JComboBox[dersSayisi];
        dersler=new JLabel[dersSayisi];
        
        JLabel baslik1=new JLabel("DERSLER");
        baslik1.setBounds(40,10,100,30);
        add(baslik1);
        
        JLabel baslik2=new JLabel("KREDILER");
        baslik2.setBounds(150,10,100,30);
        add(baslik2);
        
        JLabel baslik3=new JLabel("NOTLAR");
        baslik3.setBounds(260,10,100,30);
        add(baslik3);
        
        for(int i = 0; i<dersSayisi; i++){
            
            ders="Ders "+(i+1);
            
            dersler[i] = new JLabel(ders);
            dersler[i].setBounds(40,dersY,50,50);
            add(dersler[i]);
            
            krediler[i] = new JComboBox<>(items);
            krediler[i].setBounds(150,dersY+11,60,30);
            add(krediler[i]);
            
            notlar[i] = new JComboBox<>(notItems);
            notlar[i].setBounds(260,dersY+11,60,30);
            add(notlar[i]);

            dersY+=aralik+20;
            
            
        }
        
        if(gpaHesapla){
            
            JLabel genelgpa=new JLabel("Bu zamana kadar ki Gpa' nizi giriniz(3.30):");
            genelgpa.setBounds(40,dersY+50,300,30);
            add(genelgpa);
             gpaGiris=new JTextField("");
            gpaGiris.setBounds(280,dersY+50,60,30);
            add(gpaGiris);
            
            JLabel alinmisKredi=new JLabel("Bu zamana kadar ki aldiginiz kredinizi giriniz(90):");
            alinmisKredi.setBounds(40,dersY+100,300,30);
            add(alinmisKredi);
            krediGiris=new JTextField("");
            krediGiris.setBounds(310,dersY+100,60,30);
            add(krediGiris);
            
            
        }
        
        JButton hesapla=new JButton("HESAPLA");
        hesapla.setBounds(350,100,100,40);
        hesapla.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                   System.out.println(dersSayisi);
        double toplam=0;
        int kreditoplam=0;
        for(int i = 0; i<dersSayisi; i++){
 
            int kredi = krediler[i].getItemAt(krediler[i].getSelectedIndex());
            kreditoplam+=kredi;
            toplam+=kredi*harfliyiDonus(notlar[i].getItemAt(notlar[i].getSelectedIndex()));
             
        }
        double yano =  (double)toplam/kreditoplam;
        //System.out.println("yano"+ yano);
        if(gpaHesapla){
            
            double geneltoplam = Double.valueOf(krediGiris.getText())*Double.valueOf(gpaGiris.getText())+yano*kreditoplam;
            double gpa = geneltoplam/(kreditoplam+Double.valueOf(krediGiris.getText()));
            
            JOptionPane.showMessageDialog(null,"Yano: "+yano+"\n Gano: "+gpa);
            System.exit(0);
        }
        else{
            JOptionPane.showMessageDialog(null,"Yano : "+yano);
            System.exit(0);
       
        }
            }
        });
        add(hesapla);
        
      
        setSize(500,500);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }

    
    public double harfliyiDonus(String harflinot){
        
        switch (harflinot) {
            case "AA":
                return 4.0;
            case "BA":
                return 3.5;
            case "BB":
                return 3.0;
            case "CB":
                return 2.50;
            case "CC":
                return 2.0;
            case "DC":
                return 1.5;
            case "DD":
                return 1.0;
            case "FD":
                return 0.5;
            default:
                return 0.0;
        }
        
        
    }
    
    
    
}
