package fr.progz.io.yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigFile {

    private File _file;
    private FileConfiguration _config;

    private String _dir;
    private String _fileName;
    private String _defaultFile;
    private Class<? extends Object> _class;

    // =========================================================================
    // SECTION Constructors
    // =========================================================================

    /**
     * @param c the getClass() of the calling class
     * @param dir the path of the config dir of the plugin (from the dir of the server.jar)
     * @param name the path of the out file without the extension (ex "test" will resut in ./configDir/innerDir/test.yml)
     * @param defaultFile the path of the default file within the jar 'ressources/defaultFile' (without the extension)
     */
    public ConfigFile(Class<? extends Object> c, String configDir, String innerDir,String outFile, String defaultFile) {
        // Creating the File instance
        _class = c;
        _defaultFile = defaultFile;
        _dir = (configDir.substring(configDir.length() - 1).equals("/")) ? configDir : configDir + "/";
        _dir = _dir + ((innerDir.substring(innerDir.length() - 1).equals("/")) ? innerDir : innerDir + "/");
        _fileName = String.format("%s.yml", outFile);
        _file = new File(String.format("%1$s%2$s", _dir, _fileName));
        checkExist();
        _config = YamlConfiguration.loadConfiguration(_file);
    }

    private void checkExist() {
        if (!_file.exists()) {
            System.out.println(String.format("File %1$s%2$s not found, creating it",_dir,_fileName));
            _file.getParentFile().mkdirs();
            if (_defaultFile != null) importDefault();
        }
    }

    private void importDefault() {
        try {
            InputStream in = _class.getResourceAsStream(String.format("/%s.yml",_defaultFile)); // Read
            Files.copy(in,Paths.get(_file.getAbsolutePath())); // Write
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    // !SECTION

    // #####################################################
    // SECTION GETTERS / SETTERS
    // #####################################################
    public FileConfiguration getConfig() {return _config;}
}