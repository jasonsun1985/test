package com.tec.pattern.chain;

import java.util.function.Consumer;

public interface NewProcessor {
	Consumer<String> process(String param);
}
