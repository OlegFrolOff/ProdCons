package com.orakul0187.factories;

import com.orakul0187.entities.BankAccount;
import com.orakul0187.other.Rand;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BankAccountFactory implements BankAccGrantor {
    private int accountNumber = 1;
    private Map<Integer, Map<String, List<String>>> clientsDataValues;


    public BankAccountFactory() {
        String folderPath = "D:\\learning-project\\NeoProject\\ProdCons\\common_lib\\src\\main\\resources\\usernames";
        try {
            getFromTextFiles(folderPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BankAccount getBankAccount() {
        return createRandomAccount();
    }

    private void getFromTextFiles(String folderPath) throws IOException {
        clientsDataValues = new HashMap<>();
        clientsDataValues.put(0, new HashMap<>());
        clientsDataValues.put(1, new HashMap<>());
        File[] userNamesFiles = Paths.get(folderPath).toFile().listFiles();
        for (File file : userNamesFiles) {
            switch (file.getName()) {
                case "MansNames.txt":
                    clientsDataValues.get(0).put("names", Files.readAllLines(file.toPath()));
                    break;
                case "MansPatronymic.txt":
                    clientsDataValues.get(0).put("patronymics", Files.readAllLines(file.toPath()));
                    break;
                case "MansSurnames.txt":
                    clientsDataValues.get(0).put("surnames", Files.readAllLines(file.toPath()));
                    break;
                case "WomansNames.txt":
                    clientsDataValues.get(1).put("names", Files.readAllLines(file.toPath()));
                    break;
                case "WomansPatronymic.txt":
                    clientsDataValues.get(1).put("patronymics", Files.readAllLines(file.toPath()));
                    break;
                case "WomansSurnames.txt":
                    clientsDataValues.get(1).put("surnames", Files.readAllLines(file.toPath()));
                    break;
                default:
                    break;
            }
        }
    }

    private BankAccount createRandomAccount() {
        UUID uuid = UUID.randomUUID();
        String firstName;
        String lastName;
        String patronymic;
        int accountNumber = this.accountNumber++;
        int gender = Rand.randomInt(0, 1);
        int namesLength = clientsDataValues.get(gender).get("names").size();
        int surnamesLength = clientsDataValues.get(gender).get("surnames").size();
        int patronymicsLength = clientsDataValues.get(gender).get("patronymics").size();

        firstName = clientsDataValues.get(gender).get("names").get(Rand.randomInt(0, namesLength - 1));
        patronymic = clientsDataValues.get(gender).get("patronymics").get(Rand.randomInt(0, patronymicsLength - 1));
        lastName = clientsDataValues.get(gender).get("surnames").get(Rand.randomInt(0, surnamesLength - 1));
        return new BankAccount(uuid, firstName, lastName, patronymic, accountNumber);
    }
}
