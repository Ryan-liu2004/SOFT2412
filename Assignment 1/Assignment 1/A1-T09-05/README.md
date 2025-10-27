# Currency Converter

A console-based currency converter implemented in Java with Gradle and JUnit. The project follows the **Integration‑Manager (Main) workflow** and preserves history using **merge commits** (no fast-forward).

## Team Members
- Haochen Liu (hliu0010)
- Shirui Yang (syan0724)
- Tingrui Chen (tche0266)
- Yanyu Zhang (yzha0949)


## Quick Start
```bash
# Using the Gradle Wrapper (recommended)
./gradlew build     # Build project
./gradlew test      # Run tests
./gradlew run       # Run application

# On Windows:
# gradlew.bat build && gradlew.bat test && gradlew.bat run
```

## Features
- Supported currencies: `USD`, `EUR`, `GBP`, `AUD`.
- **Hard-coded rates** (example baseline): USD=1.00, EUR=0.85, GBP=0.75, AUD=1.30.
- Same-currency conversions are always `1.00`.
- All numeric outputs are formatted to **two decimal places**.
- Console menu:
  1) Convert Currency  
  2) View Exchange Rates  
  3) Exit  
  The app loops back to the main menu until the user chooses *Exit*.
- Input validation for currency codes and amounts; invalid inputs produce friendly messages without crashing.

## Project Structure
```
src/
 └─ main/java/
     ├─ App.java                # Program entry point; boots UI
     ├─ UserInterface.java      # Console I/O, menu, and flow
     ├─ CurrencyConverter.java  # Conversion logic and rates
     ├─ DataValidator.java      # Input validation & parsing
     └─ RateDisplay.java        # Tabular/single-pair rate display
 └─ test/java/                  # JUnit 5 tests
build.gradle
```

## Work Allocation
- Haochen Liu (hliu0010) → 1st method in each class
- Shirui Yang (syan0724) → 2nd method in each class
- Tingrui Chen (tche0266) → 3rd method in each class
- Yanyu Zhang (yzha0949) → 4th method in each class
App.java and App.test — completed jointly


## Individual Contributions
### hliu0010
- convert(), isValidCurrency(), showAllRatesTable(), start()
- build.gradle, information on README.md
- test all functions above ,test testHandleConversion()


### syan0724
- getSupportedCurrencies(), isValidAmount(), showRate(), showMenu()
- build.gradle, information on README.md
- test all functions above ,test testConvertSameCurrency() and testConvertZeroAmount()

### tche0266:
- getExchangeRate(), parseAmount(), showAllPairs(), handleConversion()
- fix conflict, information on README.md
- test all functions above, also write the initial version of AppTest.java


### yzha0949
- roundToTwoDecimals(), normalizeCurrency(), showSameCurrencyRates(), showExchangeRates()
- fix conflict, information on README.md
- test all functions above 



## Merge Commits (Evidence)
The assignment requires visible **merge commits** (no squashing, no fast-forward). Provide examples from your history:
### hliu0010
- Merge hliu0010: `feature/CurrencyConverter/convert` (`c644c3f`)
- Merge hliu0010: `feature/DataValidator/isValidCurrency` (`b9789f0`)
- Merge hliu0010: `feature/RateDisplay/showAllRatesTable` (`91264d8`)
- Merge hliu0010: `feature/UserInterface/start` (`6fad223`)
- Merge hliu0010: `test/CurrencyConverterTest/testConvertUSDToEUR` (`5131d55`)
- Merge hliu0010: `test/DataValidatorTest/testValidCurrencies` (`652cc7d`)
- Merge hliu0010: `test/RateDisplayTest/testShowAllRatesTable` (`5aa2ba5`)
- Merge hliu0010: `test/UserInterfaceTest/testStartMethod` (`b667e00`)
- Merge hliu0010: `test/UserInterfaceTest/handleConversion` (`2d96e7d`)


### syan0724
- Merge syan0724: `feature/DataValidator/is-valid-amount` (`ce9f5e8`)
- Merge syan0724: `feature/CurrencyConverter/get-supported-currencies` (`7350d58`)
- Merge syan0724: `feature/RateDisplay/show-rate` (`84dadc9`)
- Merge syan0724: `feature/UI/show-menu` (`2c1703d`)
- Merge syan0724: `test/CurrencyConverter/convert-same-currency` (`d49798d`)
- Merge syan0724: `test/CurrencyConverter/convert-zero-amount` (`339b5a7`)
- Merge syan0724: `test/CurrencyConverter/get-supported-currencies` (`91d8672`)
- Merge syan0724: `test/DataValidator/is-valid-amount` (`5e36dde`)
- Merge syan0724: `test/RateDisplay/show-rate` (`0916648`)
- Merge syan0724: `test/UI/show-menu` (`4ddb208`)

### tche0266
- Merge tche0266: `feature/validator/parse-amount`(`f9302f3`)
- Merge tche0266: `feature/ui/handle-currency-conversion`(`fa5c8f6`)
- Merge tche0266: `feature/display/display-exchange-rates`(`4c42b4c`)
- Merge tche0266: `feature/converter/get-exchange-rate`(`9d8511c`)
- Merge tche0266: `test/validator/parse-amount`(`ec021d4`)
- Merge tche0266: `test/ui/handle-currency-conversion`(`5c2009d`)
- Merge tche0266: `test/display/display-exchange-rates`(`652b863`)
- Merge tche0266: `test/converter/get-exchange-rate`(`fc38f5d`)
- Merge tche0266: `test/apptest`(`36de7be`)

### yzha0949
- Merge yzha0949: `feature/DataValidator/normalizeCurrency` (`c2a0ac5`)
- Merge yzha0949: `feature/CurrencyConverter/roundToTwoDecimals`
- Merge yzha0949: `feature/RateDisplay/showSameCurrencyRates`
- Merge yzha0949: `feature/UserInterface/showExchangeRates`
- Merge yzha0949: `test/DataValidator/normalizeCurrency` (`065ba2c`)
- Merge yzha0949: `test/CurrencyConverter/roundToTwoDecimals`
- Merge yzha0949: `test/RateDisplay/showSameCurrencyRates`
- Merge yzha0949: `test/UserInterface/showExchangeRates`



## Git Workflow
- **Individual repos:** develop on `feature/*` branches branching from `master`/`main`.
- **IM repo:** keeps a single `master`/`main`; **no feature branches are pushed** to the IM repo.
- **Integration:** the IM merges each contributor’s feature branch into IM’s `master`/`main` using `--no-ff`.
- **Synchronization:** each student merges IM’s `master`/`main` back into their own `master`/`main` to keep their personal repo up to date.

## Testing
- Framework: **JUnit 5** (`org.junit.jupiter`).
- Scope: core conversion logic (fixed rates, same-currency=1.00, two-decimal rounding), validation (valid/invalid inputs), and critical UI paths (by capturing console output where applicable).
- Run tests with `./gradlew test`.

## Example (CLI)
```
=== Currency Converter ===
1. Convert Currency
2. View Exchange Rates
3. Exit
Enter choice: 1

Enter amount: 50
From currency (USD/EUR/GBP/AUD): USD
To currency (USD/EUR/GBP/AUD): EUR
Result: 50.00 USD = 42.50 EUR

Returning to main menu...
```


## Submission Checklist
- [ ] `./gradlew build`, `./gradlew test`, and `./gradlew run` all succeed.
- [ ] Outputs are formatted to two decimal places; same-currency = `1.00`.
- [ ] README includes **Team Members**, **Work Allocation**, and **Merge Commit** evidence.
- [ ] Each student’s personal repository contains the **fully integrated** application and is pushed.

## API Documentation

The complete API reference can be found here: [Javadoc](./docs/javadoc/index.html)
