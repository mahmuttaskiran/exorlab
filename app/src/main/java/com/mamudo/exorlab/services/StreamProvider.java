package com.mamudo.exorlab.services;

import com.mamudo.exorlab.models.Stream;

import java.util.List;

public interface StreamProvider {
  // Let suppose our implementations must implement pagination
  List<Stream> load();
}
