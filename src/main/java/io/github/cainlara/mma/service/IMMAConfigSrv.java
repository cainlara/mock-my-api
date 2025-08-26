package io.github.cainlara.mma.service;

import java.io.IOException;

import io.github.cainlara.mma.domain.MMAVersionDescription;

public interface IMMAConfigSrv {

  MMAVersionDescription getVersionDescription() throws IOException;
}
