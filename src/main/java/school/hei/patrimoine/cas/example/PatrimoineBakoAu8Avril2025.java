package school.hei.patrimoine.cas.example;

import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static school.hei.patrimoine.modele.Argent.ariary;
import static school.hei.patrimoine.modele.Devise.MGA;

import java.time.LocalDate;
import java.util.Set;
import java.util.function.Supplier;
import school.hei.patrimoine.modele.Patrimoine;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.Compte;
import school.hei.patrimoine.modele.possession.FluxArgent;
import school.hei.patrimoine.modele.possession.Materiel;
import school.hei.patrimoine.modele.possession.Possession;
import school.hei.patrimoine.modele.possession.TransfertArgent;

public class PatrimoineBakoAu8Avril2025 implements Supplier<Patrimoine> {

    private static final LocalDate AU_8_AVRIL_2025 = LocalDate.of(2025, APRIL, 8);
    private static final LocalDate FIN_ANNEE = LocalDate.of(2025, DECEMBER, 31);

    private Set<Possession> possessionsBako(
            Compte compteBNI,
            Compte compteBMOI,
            Compte coffreFort,
            Materiel ordinateur)    {

        new FluxArgent(
                "Salaire net",
                compteBNI,
                AU_8_AVRIL_2025,
                FIN_ANNEE,
                2,
                ariary(2_125_000));

        new TransfertArgent(
                "Épargne mensuelle",
                compteBNI,
                compteBMOI,
                AU_8_AVRIL_2025.plusDays(1),
                FIN_ANNEE,
                3,
                ariary(200_000));

        new FluxArgent(
                "Loyer colocation",
                compteBNI,
                AU_8_AVRIL_2025,
                FIN_ANNEE,
                26,
                ariary(-600_000));

        new FluxArgent(
                "Dépenses mensuelles",
                compteBNI,
                AU_8_AVRIL_2025,
                FIN_ANNEE,
                1,
                ariary(-700_000));

        return Set.of(compteBNI, compteBMOI, coffreFort, ordinateur);
    }

    private Compte compteBNI() {
        return new Compte("Compte courant BNI", AU_8_AVRIL_2025, ariary(2_000_000));
    }

    private Compte compteBMOI() {
        return new Compte("Compte épargne BMOI", AU_8_AVRIL_2025, ariary(625_000));
    }

    private Compte coffreFort() {
        return new Compte("Coffre-fort maison", AU_8_AVRIL_2025, ariary(1_750_000));
    }

    private Materiel ordinateur() {
        return new Materiel(
                "Ordinateur portable",
                AU_8_AVRIL_2025,
                AU_8_AVRIL_2025,
                ariary(3_000_000),
                -0.12);
    }

    @Override
    public Patrimoine get() {
        var bako = new Personne("Bako");
        var compteBNI = compteBNI();
        var compteBMOI = compteBMOI();
        var coffreFort = coffreFort();
        var ordinateur = ordinateur();

        Set<Possession> possessions = possessionsBako(compteBNI, compteBMOI, coffreFort, ordinateur);

        return Patrimoine.of(
                "Patrimoine de Bako au 8 avril 2025",
                MGA,
                AU_8_AVRIL_2025,
                bako,
                possessions);
    }
}
