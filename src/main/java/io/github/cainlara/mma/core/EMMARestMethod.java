package io.github.cainlara.mma.core;

public enum EMMARestMethod {

  UNKNOWN {
    @Override
    public String getAlias() {
      return "Unknown";
    }
  },
  GET {
    @Override
    public String getAlias() {
      return "GET";
    }
  },
  POST {
    @Override
    public String getAlias() {
      return "POST";
    }
  },
  PUT {
    @Override
    public String getAlias() {
      return "PUT";
    }
  },
  DELETE {
    @Override
    public String getAlias() {
      return "DELETE";
    }
  };

  public abstract String getAlias();

  public static EMMARestMethod valueOfAlias(final String alias) {
    for (EMMARestMethod value : values()) {
      if (value.getAlias().equalsIgnoreCase(alias.trim())) {
        return value;
      }
    }

    return UNKNOWN;
  }
}
