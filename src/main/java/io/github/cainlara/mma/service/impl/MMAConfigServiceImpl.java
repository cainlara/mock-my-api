package io.github.cainlara.mma.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;

import io.github.cainlara.mma.domain.MMAVersionDescription;
import io.github.cainlara.mma.service.IMMAConfigSrv;
import io.github.cainlara.mma.util.MMAFileUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MMAConfigServiceImpl implements IMMAConfigSrv {

  @Override
  public MMAVersionDescription getVersionDescription() throws IOException {
    return MMAFileUtils.readGitValues();
  }
}
