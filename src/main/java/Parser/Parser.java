package Parser;

import Commands.*;
import exceptions.InvalidTaskException;
import exceptions.NoTaskDescriptionException;

public class Parser {

    public static String dataInputToUserInput(String data) {
        data = data.substring(data.indexOf("["));
        char taskType = data.charAt(1);
        int descriptionStartIndex = data.indexOf("] ", data.indexOf("]") + 1) + 2;
        String description;
        String timeDetails;
        switch (taskType) {
        case 'T':
            return "todo " + data.substring(descriptionStartIndex);
        case 'E':
            int endIndex = data.indexOf("(");
            description = data.substring(descriptionStartIndex, endIndex).trim();

            int timeIndex = data.indexOf("(from");
            timeDetails = data.substring(timeIndex).trim();
            timeDetails = timeDetails.replace("(from:", "/from");
            timeDetails = timeDetails.replace("to:", "/to");
            timeDetails = timeDetails.replace(")", "");
            return "event " + description + " " + timeDetails;
        default:
            int descriptionEndIndex = data.indexOf("(");
            description = data.substring(descriptionStartIndex, descriptionEndIndex).trim();
            int deadlineIndex = data.indexOf("(by");
            timeDetails = data.substring(deadlineIndex);
            timeDetails = timeDetails.replace("(by", "/by");
            timeDetails = timeDetails.replace(")", "");
            return "deadline " + description + " " + timeDetails;
        }
    }

    public static Command inputToCommand(String userInput) throws  InvalidTaskException{
       String strippedInput= userInput.toLowerCase().trim();
       if (strippedInput.isEmpty()) {
           return null;
       }

       String[] words = strippedInput.split(" ");
       switch (words[0]) {
       case "bye":
           return new ExitCommand();
       case "list":
           return new ListCommand();
       case "delete":
           return new DeleteCommand(Integer.parseInt(words[1]) - 1);
       case "mark":
           return new MarkCommand(Integer.parseInt(words[1]) - 1);
       case "unmark":
           return new UnmarkCommand(Integer.parseInt(words[1]) - 1);
       case "todo", "deadline", "event":
           return new AddTaskCommand(userInput);
       default:
           throw new InvalidTaskException();
       }
    }
}
















