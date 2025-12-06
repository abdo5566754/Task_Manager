package model;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AlertManager extends Thread {
    private List<Task> tasks;

    public AlertManager(List<Task> l) {
        this.tasks = l;
        setDaemon(true);
    }


    @Override
    public void run() {
        while (true) {

            try {

                for (Task t : tasks) {
                    if (t.getAlertDate().isEqual(LocalDate.now()) && (t.getAlertTime().getHour() == LocalTime.now().getHour() && t.getAlertTime().getMinute() == LocalTime.now().getMinute())) {
                        while (t.getAlertTime().getMinute() == LocalTime.now().getMinute()) {
                            playAudio();
                            sleep(3000);
                        }
                    }
                }

                sleep(10000);

            } catch (InterruptedException e) {

                break;

            }


        }
    }

    private void playAudio() {
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("alarm_beep.wav"));) {

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            Object lock = new Object();

            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    synchronized (lock) {
                        lock.notify();
                    }
                }
            });

            clip.start();
            synchronized (lock) {
                lock.wait();
            }
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}