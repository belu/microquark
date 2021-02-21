package com.melonbase.microquark.repo.data;

public enum Kanton {

  AARGAU(685_424),
  APPENZELL_AUSSERRHODEN(55_234),
  APPENZELL_INNERRHODEN(16_145),
  BASEL_LAND(288_132),
  BASEL_STADT(194_766),
  BERN(1_034_977),
  FREIBURG(318_714),
  GENF(499_480),
  GLARUS(40_403),
  GRAUBUENDEN(198_379),
  JURA(73_419),
  LUZERN(409_557),
  NEUENBURG(176_850),
  NIDWALDEN(43_223),
  OBWALDEN(37_841),
  SCHAFFHAUSEN(81_991),
  SCHWYZ(159_165),
  SOLOTHURN(273_194),
  ST_GALLEN(507_697),
  TESSIN(353_343),
  THURGAU(276_472),
  URI(36_433),
  WAADT(799_145),
  WALLIS(343_955),
  ZUG(126_837),
  ZUERICH(1_520_968);

  private final int numberOfInhabitants;

  Kanton(int numberOfInhabitants) {
    this.numberOfInhabitants = numberOfInhabitants;
  }

  public int getNumberOfInhabitants() {
    return numberOfInhabitants;
  }
}
