package Clases.Exportador;
import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDate_Adaptador implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
    private static final DateTimeFormatter Formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Override
    public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String dateStr = jsonElement.getAsString();
        return LocalDate.parse(dateStr, Formato);
    }
    @Override
    public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext jsonSerializationContext) {
        String formattedDate = localDate.format(Formato);
        return new JsonPrimitive(formattedDate);
    }
}
