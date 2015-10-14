
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author William
 */
@ManagedBean
@ViewScoped
public class BenefAll implements Serializable{
 




    private HorizontalBarChartModel horizontalBarModel;

    private List<BeneficeFilm> tab_film = null;
    

    public void return_film_exploit() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");
            // connection réussie
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select titre, prix_achat, sum(montant_total) as tot from film join recette using (id_film) group by titre order by tot desc");
            tab_film = new ArrayList<>();
            while (rs.next()) {
                BeneficeFilm b = new BeneficeFilm();
                b.setTitre(rs.getString("titre"));
                b.setMontantTot(rs.getDouble("tot"));
                b.setPrixF(rs.getDouble("prix_achat"));
                b.setBenef(rs.getDouble("tot") - rs.getDouble("prix_achat"));
                tab_film.add(b);
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
    public void init() {
        try {
            createBarModels();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CalculBenefice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }

    private void createBarModels() throws ClassNotFoundException {
        createHorizontalBarModel();
    }

    private void createHorizontalBarModel() throws ClassNotFoundException {
        return_film_exploit();
        horizontalBarModel = new HorizontalBarChartModel();
        System.out.println("je passe ici");

        BarChartModel model = new BarChartModel();
        ChartSeries benef = new ChartSeries();

        for (int j = 0; j < tab_film.size(); j++) {
            benef.setLabel("Bénéfices");
            benef.set(tab_film.get(j).getTitre(), tab_film.get(j).getBenef());
        }

        horizontalBarModel.addSeries(benef);

        horizontalBarModel.setTitle("Rentabilité des films en exploitation");
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setStacked(true);

        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel("prix en euros");
        xAxis.setMin(-100000);
        xAxis.setMax(1000000);

        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Films");
    }
    public void prepaHisto() throws ClassNotFoundException{
        return_film_exploit();
        createHorizontalBarModel();
        System.out.println("je passe dans la première classe"); 
    }
    
}
    

