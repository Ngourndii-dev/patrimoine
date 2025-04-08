package school.hei.patrimoine.cas.example;

import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static school.hei.patrimoine.modele.Argent.ariary;
import static school.hei.patrimoine.modele.Devise.MGA;

import java.time.LocalDate;
import java.util.Set;
import java.util.function.Supplier;
import school.hei.patrimoine.modele.Patrimoine;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.Compte;
import school.hei.patrimoine.modele.possession.Dette;
import school.hei.patrimoine.modele.possession.FluxArgent;
import school.hei.patrimoine.modele.possession.Materiel;
import school.hei.patrimoine.modele.possession.Possession;

public class TianaCas implements Supplier<Patrimoine> {

    private static final LocalDate AU_8_AVRIL_2025 = LocalDate.of(2025, APRIL, 8);
    private static final LocalDate FIN_SIMULATION = LocalDate.of(2026, JANUARY, 31);

    private Set<Possession> possessionsTiana(
            Compte compteBancaire,
            Materiel terrain,
            Dette pretBancaire) {

        new FluxArgent(
                "Dépenses familiales",
                compteBancaire,
                AU_8_AVRIL_2025,
                FIN_SIMULATION,
                1,
                ariary(-4_000_000));

        new FluxArgent(
                "Dépenses projet",
                compteBancaire,
                LocalDate.of(2025, java.time.Month.JUNE, 5),
                LocalDate.of(2025, DECEMBER, 5),
                5,
                ariary(-5_000_000));

        new FluxArgent(
                "Acompte projet (10%)",
                compteBancaire,
                LocalDate.of(2025, MAY, 1),
                LocalDate.of(2025, MAY, 1),
                1,
                ariary(7_000_000));

        new FluxArgent(
                "Solde projet (90%)",
                compteBancaire,
                LocalDate.of(2026, JANUARY, 31),
                LocalDate.of(2026, JANUARY, 31),
                31,
                ariary(63_000_000));

        new FluxArgent(
                "Entrée prêt bancaire",
                compteBancaire,
                LocalDate.of(2025, JULY, 27),
                LocalDate.of(2025, JULY, 27),
                27,
                ariary(20_000_000));

        new FluxArgent(
                "Création dette prêt",
                pretBancaire,
                LocalDate.of(2025, JULY, 27),
                LocalDate.of(2025, JULY, 27),
                27,
                ariary(-20_000_000));

        new FluxArgent(
                "Remboursement prêt",
                compteBancaire,
                LocalDate.of(2025, AUGUST, 27),
                LocalDate.of(2026, JULY, 27),
                27,
                ariary(-2_000_000));

        new FluxArgent(
                "Annulation dette prêt",
                pretBancaire,
                LocalDate.of(2026, JULY, 27),
                LocalDate.of(2026, JULY, 27),
                27,
                ariary(24_000_000));

        return Set.of(compteBancaire, terrain, pretBancaire);
    }

    private Compte compteBancaire() {
        return new Compte("Compte bancaire", AU_8_AVRIL_2025, ariary(60_000_000));
    }

    private Materiel terrain() {
        return new Materiel(
                "Terrain bâti",
                AU_8_AVRIL_2025,
                AU_8_AVRIL_2025,
                ariary(100_000_000),
                0.10);
    }

    private Dette pretBancaire() {
        return new Dette("Prêt bancaire", AU_8_AVRIL_2025, ariary(0));
    }

    @Override
    public Patrimoine get() {
        var tiana = new Personne("Tiana");
        var compteBancaire = compteBancaire();
        var terrain = terrain();
        var pretBancaire = pretBancaire();

        Set<Possession> possessions = possessionsTiana(compteBancaire, terrain, pretBancaire);

        return Patrimoine.of(
                "Patrimoine de Tiana au 8 avril 2025",
                MGA,
                AU_8_AVRIL_2025,
                tiana,
                possessions);
    }


}
