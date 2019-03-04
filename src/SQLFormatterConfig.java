import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;

@State(
        name = "SQLFormatterConfig",
        storages = {
                @Storage("intellij-plugin-SQLFormatter.xml")
        }
)

public class SQLFormatterConfig implements PersistentStateComponent<SQLFormatterConfig> {
    private boolean removeNewLineCode = false;

    @Override
    public SQLFormatterConfig getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull SQLFormatterConfig config) {
        XmlSerializerUtil.copyBean(config, this);
    }

    public static SQLFormatterConfig getInstance() {
        return ServiceManager.getService(SQLFormatterConfig.class);
    }

    public boolean isRemoveNewLineCode() {
        return removeNewLineCode;
    }

    public void setRemoveNewLineCode(boolean removeNewLineCode) {
        this.removeNewLineCode = removeNewLineCode;
    }
}
