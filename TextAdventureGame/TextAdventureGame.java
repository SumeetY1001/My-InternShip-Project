import java.util.Scanner;

public class TextAdventureGame {

    public static void main(String[] args) {
        // Introduction
        System.out.println("Welcome to the Text Adventure Game!");
        System.out.println("You awaken in a medieval village surrounded by dense forests and towering mountains...");

        // Start the game
        playGame();
    }

    public static void playGame() {
        Scanner scanner = new Scanner(System.in);
        String choice;

        // Storyline - Decision Point 1
        System.out.println("As you explore the village, you hear rumors of a hidden treasure guarded by mystical creatures in the nearby forest.");
        System.out.println("Do you want to:");
        System.out.println("a) Embark on a quest to find the hidden treasure");
        System.out.println("b) Consult the village sage for guidance");
        choice = scanner.nextLine().toLowerCase();

        if (choice.equals("a")) {
            System.out.println("You decide to embark on a quest to find the hidden treasure.");
            System.out.println("As you venture into the forest, you encounter a fork in the path.");
            System.out.println("Do you:");
            System.out.println("a) Take the left path, which seems well-traveled");
            System.out.println("b) Take the right path, which appears less traveled");
            choice = scanner.nextLine().toLowerCase();

            // Decision Point 2
            if (choice.equals("a")) {
                System.out.println("You choose the left path, hoping it will lead you closer to the treasure.");
                System.out.println("After walking for some time, you reach a clearing with an old shrine.");
                System.out.println("Do you:");
                System.out.println("a) Investigate the shrine for clues");
                System.out.println("b) Ignore the shrine and continue deeper into the forest");
                choice = scanner.nextLine().toLowerCase();

                // Decision Point 3
                if (choice.equals("a")) {
                    System.out.println("You investigate the shrine and find a hidden compartment with a map inside.");
                    System.out.println("The map seems to lead to the location of the hidden treasure.");
                    System.out.println("Congratulations! You have found a clue to the treasure's location!");
                } else if (choice.equals("b")) {
                    System.out.println("You decide to ignore the shrine and continue deeper into the forest.");
                    System.out.println("As you walk further, you encounter a group of bandits blocking your path.");
                    System.out.println("Do you:");
                    System.out.println("a) Attempt to negotiate with the bandits");
                    System.out.println("b) Fight the bandits to pass through");
                    choice = scanner.nextLine().toLowerCase();

                    // Decision Point 4
                    if (choice.equals("a")) {
                        System.out.println("You attempt to negotiate with the bandits, but they demand your valuables.");
                        System.out.println("Reluctantly, you give them your belongings and continue on your quest.");
                        System.out.println("Game Over! You lost your possessions but survived the encounter.");
                    } else if (choice.equals("b")) {
                        System.out.println("You choose to fight the bandits to pass through.");
                        System.out.println("After a fierce battle, you emerge victorious and continue on your quest.");
                        System.out.println("Congratulations! You defeated the bandits and continue your journey!");
                    } else {
                        System.out.println("Invalid choice. Please enter 'a' or 'b'.");
                        playGame(); // Restart the game
                    }
                } else {
                    System.out.println("Invalid choice. Please enter 'a' or 'b'.");
                    playGame(); // Restart the game
                }
            } else if (choice.equals("b")) {
                System.out.println("You choose the right path, hoping it will lead you to undiscovered territory.");
                System.out.println("As you walk deeper into the forest, you encounter a mystical creature blocking your path.");
                System.out.println("Do you:");
                System.out.println("a) Attempt to communicate with the creature");
                System.out.println("b) Find an alternative route to bypass the creature");
                choice = scanner.nextLine().toLowerCase();

                // Decision Point 3
                if (choice.equals("a")) {
                    System.out.println("You attempt to communicate with the creature, but it seems unfriendly.");
                    System.out.println("In fear, you retreat and search for an alternative route.");
                    System.out.println("Game Over! You encountered a hostile creature and retreated.");
                } else if (choice.equals("b")) {
                    System.out.println("You decide to find an alternative route to bypass the creature.");
                    System.out.println("After exploring, you find a hidden path that leads you safely around.");
                    System.out.println("Congratulations! You have successfully bypassed the creature!");
                } else {
                    System.out.println("Invalid choice. Please enter 'a' or 'b'.");
                    playGame(); // Restart the game
                }
            } else {
                System.out.println("Invalid choice. Please enter 'a' or 'b'.");
                playGame(); // Restart the game
            }
        } else if (choice.equals("b")) {
            System.out.println("You decide to consult the village sage for guidance.");
            System.out.println("The sage tells you about the legend of the hidden treasure and offers advice for your quest.");
            System.out.println("Do you:");
            System.out.println("a) Trust the sage's advice and head into the forest immediately");
            System.out.println("b) Ask the sage for more details about the treasure and its guardians");
            choice = scanner.nextLine().toLowerCase();

            // Decision Point 2
            if (choice.equals("a")) {
                System.out.println("You trust the sage's advice and head into the forest immediately.");
                System.out.println("As you journey deeper, you encounter various challenges and obstacles.");
                System.out.println("Do you:");
                System.out.println("a) Face the challenges head-on");
                System.out.println("b) Seek alternative routes to bypass the challenges");
                choice = scanner.nextLine().toLowerCase();

                // Decision Point 3
                if (choice.equals("a")) {
                    System.out.println("You face the challenges head-on, demonstrating courage and determination.");
                    System.out.println("After overcoming various obstacles, you finally find yourself at the entrance of a cave.");
                    System.out.println("Congratulations! You have found the hidden treasure!");
                } else if (choice.equals("b")) {
                    System.out.println("You seek alternative routes to bypass the challenges, exploring hidden paths.");
                    System.out.println("After navigating through the forest, you finally find yourself at the entrance of a cave.");
                    System.out.println("Congratulations! You have found the hidden treasure!");
                } else {
                    System.out.println("Invalid choice. Please enter 'a' or 'b'.");
                    playGame(); // Restart the game
                }
            } else if (choice.equals("b")) {
                System.out.println("You ask the sage for more details about the treasure and its guardians.");
                System.out.println("The sage shares tales of mythical creatures guarding the treasure and warns of hidden traps.");
                System.out.println("Do you:");
                System.out.println("a) Venture into the forest prepared for the challenges");
                System.out.println("b) Search for companions to join you on the quest");
                choice = scanner.nextLine().toLowerCase();

                // Decision Point 3
                if (choice.equals("a")) {
                    System.out.println("You venture into the forest, prepared to face the challenges ahead.");
                    System.out.println("With determination, you navigate through the forest and eventually discover the hidden treasure.");
                    System.out.println("Congratulations! You have found the hidden treasure!");
                } else if (choice.equals("b")) {
                    System.out.println("You decide to search for companions to join you on the quest for the hidden treasure.");
                    System.out.println("After gathering a group of brave adventurers, you set out together into the forest.");
                    System.out.println("With the combined skills of your companions, you successfully find the hidden treasure.");
                    System.out.println("Congratulations! You have found the hidden treasure!");
                } else {
                    System.out.println("Invalid choice. Please enter 'a' or 'b'.");
                    playGame(); // Restart the game
                }
            } else {
                System.out.println("Invalid choice. Please enter 'a' or 'b'.");
                playGame(); // Restart the game
            }
        } else {
            System.out.println("Invalid choice. Please enter 'a' or 'b'.");
            playGame(); // Restart the game
        }

        // Decision Point 5
        System.out.println("As you uncover the hidden treasure, you notice a mysterious artifact beside it.");
        System.out.println("Do you:");
        System.out.println("a) Take the artifact with you");
        System.out.println("b) Leave the artifact behind");
        choice = scanner.nextLine().toLowerCase();

        if (choice.equals("a")) {
            System.out.println("You decide to take the artifact with you.");
            System.out.println("As you pick up the artifact, you feel a surge of power coursing through you.");
            System.out.println("Congratulations! You have obtained the legendary artifact!");
        } else if (choice.equals("b")) {
            System.out.println("You choose to leave the artifact behind, fearing its unknown power.");
            System.out.println("With the hidden treasure in hand, you return to the village as a hero.");
            System.out.println("Congratulations! You have successfully completed your quest and returned safely to the village!");
        } else {
            System.out.println("Invalid choice. Please enter 'a' or 'b'.");
            playGame(); // Restart the game
        }

        // Decision Point 6
        System.out.println("As you return to the village, you encounter a group of travelers seeking help.");
        System.out.println("Do you:");
        System.out.println("a) Offer assistance to the travelers");
        System.out.println("b) Continue on your way without getting involved");
        choice = scanner.nextLine().toLowerCase();

        if (choice.equals("a")) {
            System.out.println("You offer assistance to the travelers, aiding them on their journey.");
            System.out.println("In gratitude, the travelers reward you with valuable items.");
            System.out.println("Congratulations! Your kindness has been rewarded!");
        } else if (choice.equals("b")) {
            System.out.println("You decide to continue on your way without getting involved.");
            System.out.println("As you walk away, you hear the travelers express disappointment.");
            System.out.println("Game Over! You missed an opportunity for adventure.");
        } else {
            System.out.println("Invalid choice. Please enter 'a' or 'b'.");
            playGame(); // Restart the game
        }

        // Decision Point 7
        System.out.println("While exploring the village, you stumble upon a hidden underground chamber.");
        System.out.println("Do you:");
        System.out.println("a) Enter the chamber to investigate");
        System.out.println("b) Leave the area and avoid the chamber");
        choice = scanner.nextLine().toLowerCase();

        if (choice.equals("a")) {
            System.out.println("You enter the chamber and discover ancient artifacts and treasures.");
            System.out.println("Congratulations! You have uncovered hidden treasures!");
        } else if (choice.equals("b")) {
            System.out.println("You decide to leave the area and avoid the chamber, feeling cautious.");
            System.out.println("As you walk away, you wonder about the mysteries left undiscovered.");
            System.out.println("Game Over! You chose to play it safe and missed out on adventure.");
        } else {
            System.out.println("Invalid choice. Please enter 'a' or 'b'.");
            playGame(); // Restart the game
        }

        // Decision Point 8
        System.out.println("While resting at the village inn, you overhear a conversation about a haunted mansion.");
        System.out.println("Do you:");
        System.out.println("a) Investigate the haunted mansion");
        System.out.println("b) Ignore the rumors and continue your journey");
        choice = scanner.nextLine().toLowerCase();

        if (choice.equals("a")) {
            System.out.println("You decide to investigate the haunted mansion, intrigued by the mystery.");
            System.out.println("After exploring the mansion, you uncover its dark secrets and put the spirits to rest.");
            System.out.println("Congratulations! You have solved the mystery of the haunted mansion!");
        } else if (choice.equals("b")) {
            System.out.println("You choose to ignore the rumors and continue your journey, dismissing the tales as superstition.");
            System.out.println("As you travel onwards, you wonder about the stories you left behind.");
            System.out.println("Game Over! You missed an opportunity for adventure.");
        } else {
            System.out.println("Invalid choice. Please enter 'a' or 'b'.");
            playGame(); // Restart the game
        }

        // Decision Point 9
        System.out.println("You encounter a lost child in the forest, crying for help.");
        System.out.println("Do you:");
        System.out.println("a) Help the child find their way home");
        System.out.println("b) Leave the child and continue on your journey");
        choice = scanner.nextLine().toLowerCase();

        if (choice.equals("a")) {
            System.out.println("You offer to help the child find their way home, guiding them through the forest.");
            System.out.println("In gratitude, the child's family rewards you with a valuable treasure.");
            System.out.println("Congratulations! Your kindness has been rewarded!");
        } else if (choice.equals("b")) {
            System.out.println("You choose to leave the child and continue on your journey, feeling uncertain.");
            System.out.println("As you walk away, you hear the child's cries fade into the distance.");
            System.out.println("Game Over! You chose not to help the lost child.");
        } else {
            System.out.println("Invalid choice. Please enter 'a' or 'b'.");
            playGame(); // Restart the game
        }

        // Decision Point 10
        System.out.println("A wise hermit offers you a chance to learn powerful magic.");
        System.out.println("Do you:");
        System.out.println("a) Accept the hermit's offer and study magic");
        System.out.println("b) Decline the offer and continue your journey without magic");
        choice = scanner.nextLine().toLowerCase();

        if (choice.equals("a")) {
            System.out.println("You accept the hermit's offer and begin studying magic under their guidance.");
            System.out.println("With time and dedication, you become a skilled magician, wielding powerful spells.");
            System.out.println("Congratulations! You have mastered the art of magic!");
        } else if (choice.equals("b")) {
            System.out.println("You decline the hermit's offer and continue your journey without magic, relying on your own skills.");
            System.out.println("As you travel onwards, you wonder about the mysteries of magic left unexplored.");
            System.out.println("Game Over! You chose not to pursue the path of magic.");
        } else {
            System.out.println("Invalid choice. Please enter 'a' or 'b'.");
            playGame(); // Restart the game
        }

        // Decision Point 11
        System.out.println("While traveling through the mountains, you encounter a pack of wolves blocking your path.");
        System.out.println("Do you:");
        System.out.println("a) Try to scare the wolves away");
        System.out.println("b) Retreat and find an alternate route");
        choice = scanner.nextLine().toLowerCase();

        if (choice.equals("a")) {
            System.out.println("You attempt to scare the wolves away, brandishing a nearby torch.");
            System.out.println("The wolves, intimidated by the flames, scatter and allow you to pass.");
            System.out.println("Congratulations! You successfully scared away the wolves and continue your journey!");
        } else if (choice.equals("b")) {
            System.out.println("You decide to retreat and find an alternate route to avoid the wolves.");
            System.out.println("After navigating through a different path, you safely bypass the wolves.");
            System.out.println("Congratulations! You have found an alternative route and continue your journey!");
        } else {
            System.out.println("Invalid choice. Please enter 'a' or 'b'.");
            playGame(); // Restart the game
        }

        // Decision Point 12
        System.out.println("While resting by a river, you notice a shiny object in the water.");
        System.out.println("Do you:");
        System.out.println("a) Investigate the shiny object");
        System.out.println("b) Ignore the object and continue resting");
        choice = scanner.nextLine().toLowerCase();

        if (choice.equals("a")) {
            System.out.println("You investigate the shiny object and discover a valuable gemstone.");
            System.out.println("Congratulations! You have found a precious gemstone!");
        } else if (choice.equals("b")) {
            System.out.println("You choose to ignore the object and continue resting, feeling relaxed.");
            System.out.println("As you rest, you contemplate the mysteries of the world around you.");
            System.out.println("Game Over! You chose to relax and missed the opportunity to discover treasure.");
        } else {
            System.out.println("Invalid choice. Please enter 'a' or 'b'.");
            playGame(); // Restart the game
        }

        // Decision Point 13
        System.out.println("As you journey through a dense forest, you encounter a rickety bridge spanning a chasm.");
        System.out.println("Do you:");
        System.out.println("a) Cross the bridge cautiously");
        System.out.println("b) Search for an alternate route");
        choice = scanner.nextLine().toLowerCase();

        if (choice.equals("a")) {
            System.out.println("You cautiously cross the rickety bridge, testing each step carefully.");
            System.out.println("Despite your caution, the bridge collapses midway, and you barely manage to reach the other side.");
            System.out.println("Congratulations! You survived the collapsing bridge!");
        } else if (choice.equals("b")) {
            System.out.println("You search for an alternate route to avoid the rickety bridge, exploring the surrounding area.");
            System.out.println("After some time, you discover a hidden path that leads you safely across the chasm.");
            System.out.println("Congratulations! You have found an alternative route and continue your journey!");
        } else {
            System.out.println("Invalid choice. Please enter 'a' or 'b'.");
            playGame(); // Restart the game
        }

        // Decision Point 14
        System.out.println("While traveling through a mountain pass, you encounter a group of nomads offering trade goods.");
        System.out.println("Do you:");
        System.out.println("a) Engage in trade with the nomads");
        System.out.println("b) Politely decline and continue your journey");
        choice = scanner.nextLine().toLowerCase();

        if (choice.equals("a")) {
            System.out.println("You engage in trade with the nomads, exchanging goods and stories.");
            System.out.println("In return for your trade, the nomads provide you with rare and exotic items.");
            System.out.println("Congratulations! You have obtained valuable trade goods!");
        } else if (choice.equals("b")) {
            System.out.println("You politely decline the offer and continue your journey, preferring to travel light.");
            System.out.println("As you continue onwards, you reflect on the diverse cultures you encounter.");
            System.out.println("Game Over! You chose not to engage in trade with the nomads.");
        } else {
            System.out.println("Invalid choice. Please enter 'a' or 'b'.");
            playGame(); // Restart the game
        }

        // Decision Point 15
        System.out.println("While passing through a village, you hear rumors of a powerful artifact hidden in a nearby cave.");
        System.out.println("Do you:");
        System.out.println("a) Investigate the cave for the artifact");
        System.out.println("b) Disregard the rumors and continue your journey");
        choice = scanner.nextLine().toLowerCase();

        if (choice.equals("a")) {
            System.out.println("You decide to investigate the cave for the rumored artifact, intrigued by the tales.");
            System.out.println("After exploring the cave's depths, you discover the ancient artifact.");
            System.out.println("Congratulations! You have found the powerful artifact!");
        } else if (choice.equals("b")) {
            System.out.println("You disregard the rumors and continue your journey, skeptical of the tales.");
            System.out.println("As you travel onwards, you wonder about the mysteries left undiscovered.");
            System.out.println("Game Over! You chose not to investigate the rumored artifact.");
        } else {
            System.out.println("Invalid choice. Please enter 'a' or 'b'.");
            playGame(); // Restart the game
        }

          // Decision Point 16 (Romantic Ending)
          System.out.println("As you journey through the land, you meet a fellow adventurer who steals your heart.");
          System.out.println("Do you:");
          System.out.println("a) Express your feelings and propose a journey together");
          System.out.println("b) Keep your feelings to yourself and continue on your own path");
          choice = scanner.nextLine().toLowerCase();
  
          if (choice.equals("a")) {
              System.out.println("You express your feelings to your fellow adventurer, and they reciprocate.");
              System.out.println("Together, you embark on countless adventures, facing challenges and triumphs side by side.");
              System.out.println("Congratulations! You have found love and companionship in your journey!");
          } else if (choice.equals("b")) {
              System.out.println("You choose to keep your feelings to yourself, deciding to continue on your own path.");
              System.out.println("As you journey onwards, you can't help but wonder about the adventures you could have shared.");
              System.out.println("Game Over! You chose to walk your path alone, leaving love behind.");
          } else {
              System.out.println("Invalid choice. Please enter 'a' or 'b'.");
              playGame(); // Restart the game
          }
  
          // Close the scanner
          scanner.close();
      }
  }

  