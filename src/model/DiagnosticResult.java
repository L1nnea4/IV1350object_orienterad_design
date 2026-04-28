package model;

/**
 * Represents one diagnostic result.
 */
public class DiagnosticResult {
    private final String description;

    /**
     * Creates a diagnostic result object.
     *
     * @param description The result description.
     */
    public DiagnosticResult(String description) {
        this.description = description;
    }

    /**
     * Returns result as text.
     *
     * @return Text form of diagnostic result.
     */
    @Override
    public String toString() {
        return description;
    }
}