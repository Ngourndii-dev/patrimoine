package school.hei.patrimoine.cas;

import school.hei.patrimoine.cas.example.PatrimoineBakoAu8Avril2025;
import school.hei.patrimoine.cas.example.TianaCas;
import school.hei.patrimoine.modele.Devise;
import school.hei.patrimoine.modele.Patrimoine;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.Possession;

import java.time.LocalDate;
import java.util.Set;
import java.util.function.Supplier;

import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static school.hei.patrimoine.modele.Argent.ariary;
import static school.hei.patrimoine.modele.Devise.MGA;

public class CasSetSupplier implements Supplier<CasSet> {
  private static final LocalDate AU_8_AVRIL_2025 = LocalDate.of(2025, APRIL, 8);
  private static final LocalDate FIN_SIMULATION_TIANA = LocalDate.of(2026, JANUARY, 31);
  private static final LocalDate FIN_ANNEE_BAKO = LocalDate.of(2025, DECEMBER, 31);

  @Override
  public CasSet get() {
    var bakoCas = new PatrimoineBakoAu8Avril2025();
    var bako = new Personne("Bako");
    Cas casBako = new Cas(AU_8_AVRIL_2025, FIN_ANNEE_BAKO, bako) {
      private final Patrimoine patrimoineBako = bakoCas.get();

      @Override
      protected Devise devise() {
        return MGA;
      }

      @Override
      protected String nom() {
        return "Patrimoine de Bako au 8 avril 2025";
      }

      @Override
      protected void init() {
      }

      @Override
      protected void suivi() {
      }

      @Override
      public Set<Possession> possessions() {
        return patrimoineBako.getPossessions();
      }
    };

    var tianaCas = new TianaCas();
    var tiana = new Personne("Tiana");
    Cas casTiana = new Cas(AU_8_AVRIL_2025, FIN_SIMULATION_TIANA, tiana) {
      private final Patrimoine patrimoineTiana = tianaCas.get();

      @Override
      protected Devise devise() {
        return MGA;
      }

      @Override
      protected String nom() {
        return "Patrimoine de Tiana au 8 avril 2025";
      }

      @Override
      protected void init() {
      }

      @Override
      protected void suivi() {
      }

      @Override
      public Set<Possession> possessions() {
        return patrimoineTiana.getPossessions();
      }
    };

    return new CasSet(
            Set.of(casBako, casTiana),
            ariary(2_000)
    );
  }
}
