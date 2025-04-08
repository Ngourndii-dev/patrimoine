package school.hei.patrimoine.cas;
import org.junit.jupiter.api.Test;
import school.hei.patrimoine.cas.example.PatrimoineBakoAu8Avril2025;
import school.hei.patrimoine.cas.example.TianaCas;
import school.hei.patrimoine.modele.Patrimoine;

import java.time.LocalDate;

import static java.time.Month.DECEMBER;
import static java.time.Month.MARCH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static school.hei.patrimoine.modele.Argent.ariary;

public class CalculTotalPatrimoine {

    @Test
    public void testPatrimoineBakoAu31Decembre2025() {
        PatrimoineBakoAu8Avril2025 patrimoineBakoSupplier = new PatrimoineBakoAu8Avril2025();
        Patrimoine patrimoineInitial = patrimoineBakoSupplier.get();
        LocalDate dateFin = LocalDate.of(2025, DECEMBER, 31);
        Patrimoine patrimoineFin = patrimoineInitial.projectionFuture(dateFin);

        assertEquals(ariary(108_137_500), patrimoineFin.getValeurComptable(), "Le patrimoine de Bako au 31/12/2025 devrait être 108 137 500 MGA");
    }

    @Test
    public void testPatrimoineTianaAu31Mars2026() {
        TianaCas patrimoineTianaSupplier = new TianaCas();
        Patrimoine patrimoineInitial = patrimoineTianaSupplier.get();
        LocalDate dateFin = LocalDate.of(2026, MARCH, 31);
        Patrimoine patrimoineFin = patrimoineInitial.projectionFuture(dateFin);
        assertEquals(ariary(151_750_000), patrimoineFin.getValeurComptable(), "Le patrimoine de Tiana au 31/03/2026 devrait être 151 750 000 MGA");
    }
}