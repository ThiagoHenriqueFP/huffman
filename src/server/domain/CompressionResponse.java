package server.domain;

import java.util.Map;

public class CompressionResponse {
    private final SymbolNode symbolNode;
    private final String value;

    public CompressionResponse(SymbolNode symbolNode, String value) {
        this.symbolNode = symbolNode;
        this.value = value;
    }

    public SymbolNode getSymbolNode() {
        return symbolNode;
    }

    public String getValue() {
        return value;
    }
}
