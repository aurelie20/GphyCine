/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author William
 */
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;

@ManagedBean
@ViewScoped
public class BenefAll implements Serializable{

    private HorizontalBarChartModel horizontalBarModel2;

    private List<BeneficeFilm> tab_film2 = null;
    

    public void return_film_exploit2() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");
            // connection réussie
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select titre, prix_achat, sum(montant_total) as tot from film join recette using (id_film)  group by titre order by tot desc");
            tab_film2 = new ArrayList<>();
            while (rs.next()) {
                BeneficeFilm b2 = new BeneficeFilm();
                b2.setTitre(rs.getString("titre"));
                b2.setMontantTot(rs.getDouble("tot"));
                b2.setPrixF(rs.getDouble("prix_achat"));
                b2.setBenef(rs.getDouble("tot") - rs.getDouble("prix_achat"));
                tab_film2.add(b2);
            }
            System.out.println("j'ai fais la requête");

        } catch (SQLException ex) {
            System.out.println("SQLException:" + ex.getMessage());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CalculBenefice.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                System.out.print("");
                System.out.println("pas de rep");
            }
        }
    }
    
@PostConstruct
    public void init2() {
       
        try {
            createBarModels2();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BenefAll.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public HorizontalBarChartModel getHorizontalBarModel2() {
        return horizontalBarModel2;
    }

    private void createBarModels2() throws ClassNotFoundException {
        createHorizontalBarModel2();
    }

    private void createHorizontalBarModel2() throws ClassNotFoundException {
        return_film_exploit2();
        horizontalBarModel2 = new HorizontalBarChartModel();
        System.out.println("je passe ici");

        BarChartModel model2 = new BarChartModel();
        ChartSeries benef2 = new ChartSeries();

        for (int j = 0; j < tab_film2.size(); j++) {
            benef2.setLabel("Bénéfices");
            benef2.set(tab_film2.get(j).getTitre(), tab_film2.get(j).getBenef());
        }

        horizontalBarModel2.addSeries(benef2);

        horizontalBarModel2.setTitle("Rentabilité de tous les films");
        horizontalBarModel2.setLegendPosition("e");
        horizontalBarModel2.setStacked(true);

        Axis xAxis = horizontalBarModel2.getAxis(AxisType.X);
        xAxis.setLabel("prix en euros");
        xAxis.setMin(-100000);
        xAxis.setMax(1000000);

        Axis yAxis = horizontalBarModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Films");
    }
    public void prepaHisto2() throws ClassNotFoundException{
        return_film_exploit2();
        createHorizontalBarModel2();
        System.out.println("je passe dans la première classe"); 
    }
    
}
