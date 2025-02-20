import java.util.List;
import java.util.ArrayList;

class TestOutputConfig {
    private List<String> outputData = new ArrayList<>();

    public void writeOutput(String output) {
        outputData.add(output);
    }

    public List<String> getOutputData() {
        return outputData;
    }
}

