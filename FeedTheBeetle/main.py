import math
import pygame
import random

pygame.init()
pygame.mixer.init()

screen_width = 700
screen_height = 500

screen = pygame.display.set_mode((screen_width, screen_height))

pygame.display.set_caption("Feed the Beetle")
fruits = pygame.image.load("Assets/Game/fruits.png")
beetle = pygame.image.load("Assets/Game/beetle.png")
background_1 = pygame.image.load("Assets/Game/background_1.png")
background_2 = pygame.image.load("Assets/Game/background_2.png")
background_3 = pygame.image.load("Assets/Game/background_3.png")
back_button = pygame.image.load("Assets/Button/back_button.png")
next_button = pygame.image.load("Assets/Button/next_button.png")
pause_button = pygame.image.load("Assets/Button/pause_button.png")
play_button = pygame.image.load("Assets/Button/play_button.png")
previous_button = pygame.image.load("Assets/Button/previous_button.png")
settings_button = pygame.image.load("Assets/Button/settings_button.png")
beetle_sound = pygame.mixer.Sound('Assets/Audio/beetle_sound.wav')
button_sound = pygame.mixer.Sound('Assets/Audio/button_sound.mp3')

score = 0
high_score = 0
life = 3
state = 2
background = [background_1, background_2, background_3]
selected_background = 0

clock = pygame.time.Clock()
fps = 60


class Pause:
    def __init__(self):
        self.backButton = {"x": screen_width - 60,
                           "y": 10,
                           "width": 50,
                           "height": 50
                           }
        self.playButton = {"x": screen_width - 60,
                           "y": 70,
                           "width": 50,
                           "height": 50
                           }

    def render(self):
        game_text = "PAUSED"
        font = pygame.font.Font("C:/WINDOWS/FONTS/IMPACT.TTF", 45)
        game_text_render = font.render(game_text, True, (0, 0, 0))
        text_width, text_height = font.size(game_text)
        screen.blit(game_text_render, ((screen_width - text_width) / 2, (screen_height - text_height) / 2))
        screen.blit(pygame.transform.scale(back_button, (self.backButton["width"], self.backButton["height"])),
                    (self.backButton["x"], self.backButton["y"]))
        screen.blit(pygame.transform.scale(play_button, (self.playButton["width"], self.playButton["height"])),
                    (self.playButton["x"], self.playButton["y"]))

    def handle_click(self, x, y):
        global state
        if x > self.backButton["x"] and x < self.backButton["x"] + self.backButton["width"] and y > self.backButton[
            "y"] and y < self.backButton["y"] + self.backButton["height"]:
            button_sound.stop()
            button_sound.play()
            state = 2
        if x > self.playButton["x"] and x < self.playButton["x"] + self.playButton["width"] and y > self.playButton[
            "y"] and y < self.playButton["y"] + self.playButton["height"]:
            button_sound.stop()
            button_sound.play()
            pygame.mixer.music.unpause()
            state = 1


class Settings:
    def __init__(self):
        self.backButton = {"x": 10,
                           "y": 10,
                           "width": 50,
                           "height": 50
                           }
        self.previousButton = {"x": ((screen_width - 50) / 2) - 100,
                               "y": (screen_height - 50) / 2,
                               "width": 50,
                               "height": 50
                               }
        self.nextButton = {"x": ((screen_width - 50) / 2) + 100,
                           "y": (screen_height - 50) / 2,
                           "width": 50,
                           "height": 50
                           }

    def render(self):
        game_text = "SETTINGS"
        selected_background_text = str(selected_background + 1)
        font = pygame.font.Font("C:/WINDOWS/FONTS/IMPACT.TTF", 45)
        game_text_render = font.render(game_text, True, (0, 0, 0))
        selected_background_text_render = font.render(selected_background_text, True, (0, 0, 0))
        text_width_1, text_height_1 = font.size(game_text)
        text_width_2, text_height_2 = font.size(selected_background_text)
        screen.blit(game_text_render, ((screen_width - text_width_1) / 2, 100))
        screen.blit(selected_background_text_render,
                    ((screen_width - text_width_2) / 2, (screen_height - text_height_2) / 2))
        screen.blit(
            pygame.transform.scale(back_button, (self.backButton["width"], self.backButton["height"])),
            (self.backButton["x"], self.backButton["y"]))
        screen.blit(
            pygame.transform.scale(previous_button, (self.previousButton["width"], self.previousButton["height"])),
            (self.previousButton["x"], self.previousButton["y"]))
        screen.blit(
            pygame.transform.scale(next_button, (self.nextButton["width"], self.nextButton["height"])),
            (self.nextButton["x"], self.nextButton["y"]))

    def handle_click(self, x, y):
        global state
        global selected_background
        if x > self.backButton["x"] and x < self.backButton["x"] + self.backButton["width"] and y > self.backButton[
            "y"] and y < self.backButton["y"] + self.backButton["height"]:
            button_sound.stop()
            button_sound.play()
            state = 2
        if x > self.previousButton["x"] and x < self.previousButton["x"] + self.previousButton["width"] and y > \
                self.previousButton["y"] and y < self.previousButton["y"] + self.previousButton["height"]:
            button_sound.stop()
            button_sound.play()
            selected_background -= 1
            if selected_background < 0:
                selected_background = len(background) - 1
        if x > self.nextButton["x"] and x < self.nextButton["x"] + self.nextButton["width"] and y > self.nextButton[
            "y"] and y < self.nextButton["y"] + self.nextButton["height"]:
            button_sound.stop()
            button_sound.play()
            selected_background += 1
            if selected_background >= len(background):
                selected_background = 0


class Menu:
    def __init__(self):
        self.playButton = {"x": (screen_width - 200) / 2,
                           "y": (screen_height - 200) / 2,
                           "width": 200,
                           "height": 200
                           }
        self.settingsButton = {"x": screen_width - 60,
                               "y": 10,
                               "width": 50,
                               "height": 50
                               }

    def render(self):
        game_text = "FEED THE BEETLE"
        high_score_text = "HIGHSCORE: " + str(high_score)
        font_1 = pygame.font.Font("C:/WINDOWS/FONTS/IMPACT.TTF", 60)
        font_2 = pygame.font.Font("C:/WINDOWS/FONTS/IMPACT.TTF", 25)
        game_text_render = font_1.render(game_text, True, (0, 0, 0))
        high_score_text_render = font_2.render(high_score_text, True, (0, 0, 0))
        text_width_1, text_height_1 = font_1.size(game_text)
        text_width_2, text_height_2 = font_2.size(high_score_text)
        screen.blit(game_text_render, ((screen_width - text_width_1) / 2, 25))
        screen.blit(high_score_text_render, (10, screen_height - text_height_2 - 10))
        screen.blit(pygame.transform.scale(play_button, (self.playButton["width"], self.playButton["height"])),
                    (self.playButton["x"], self.playButton["y"]))
        screen.blit(
            pygame.transform.scale(settings_button, (self.settingsButton["width"], self.settingsButton["height"])),
            (self.settingsButton["x"], self.settingsButton["y"]))

    def handle_click(self, x, y):
        global state
        if x > self.playButton["x"] and x < self.playButton["x"] + self.playButton["width"] and y > self.playButton[
            "y"] and y < self.playButton["y"] + self.playButton["height"]:
            button_sound.stop()
            button_sound.play()
            state = 1
        if x > self.settingsButton["x"] and x < self.settingsButton["x"] + self.settingsButton["width"] and y > \
                self.settingsButton["y"] and y < self.settingsButton["y"] + self.settingsButton["height"]:
            button_sound.stop()
            button_sound.play()
            state = 3


class Game:
    def __init__(self):
        self.beetle = Beetle()
        self.fruits = []
        self.create_fruits()
        self.fruitTimer = 0
        self.fruitInterval = 30
        self.pauseButton = {"x": screen_width - 60,
                            "y": 10,
                            "width": 50,
                            "height": 50
                            }

    def update(self):
        self.beetle.update()
        if self.fruitTimer == self.fruitInterval:
            self.create_fruits()
            self.fruitTimer = 0
        else:
            self.fruitTimer += 1
        for fruit in self.fruits:
            fruit.update()
        self.collision()
        self.fruits = list(filter(lambda x: not x.delete, self.fruits))

    def draw(self):
        for fruit in self.fruits:
            fruit.draw()
        self.beetle.draw()
        screen.blit(
            pygame.transform.scale(pause_button, (self.pauseButton["width"], self.pauseButton["height"])),
            (self.pauseButton["x"], self.pauseButton["y"]))

    def create_fruits(self):
        x = random.randint(-100, screen_width + 100)
        y = random.randint(-100, screen_height + 100)
        if x > -50 and x < screen_width + 50 and y > -50 and y < screen_height + 50:
            self.create_fruits()
        else:
            self.fruits.append(Fruit(x, y))

    def collision(self):
        for fruit in self.fruits:
            distance = math.sqrt((self.beetle.collisionX - fruit.x) ** 2 + (self.beetle.collisionY - fruit.y) ** 2)
            if distance < self.beetle.collisionSize + fruit.size:
                beetle_sound.stop()
                beetle_sound.play()
                fruit.delete = True
                global score
                score += 1

    def show_score(self):
        font = pygame.font.Font("C:/WINDOWS/FONTS/IMPACT.TTF", 20)
        score_text = font.render("SCORE: " + str(score), True, (0, 0, 0))
        life_text = font.render("LIFE: " + str(life), True, (0, 0, 0))
        screen.blit(score_text, (10, 10))
        screen.blit(life_text, (10, 30))

    def reset_game(self):
        global score
        global life
        score = 0
        life = 3
        self.beetle.angle = 0
        self.beetle.frameBeetle = 0
        self.beetle.animateTimer = 0
        self.fruits.clear()
        self.fruits = list(filter(lambda x: False, self.fruits))

    def handle_click(self, x, y):
        global state
        if x > self.pauseButton["x"] and x < self.pauseButton["x"] + self.pauseButton["width"] and y > \
                self.pauseButton["y"] and y < self.pauseButton["y"] + self.pauseButton["height"]:
            button_sound.stop()
            button_sound.play()
            pygame.mixer.music.pause()
            state = 4


class Beetle:
    def __init__(self):
        self.x = screen_width / 2
        self.y = screen_height / 2
        self.size = 200
        self.angle = 0
        self.frameBeetle = 0
        self.frameSize = 50
        self.animateTimer = 0
        self.animateInterval = 5
        self.collisionX = self.x + 50
        self.collisionY = self.y
        self.collisionSize = 15

    def update(self):
        if self.animateTimer == self.animateInterval:
            self.frameBeetle += 1
            if self.frameBeetle == 5:
                self.frameBeetle = 0
            self.animateTimer = 0
        else:
            self.animateTimer += 1

    def draw(self):
        rotated_beetle = pygame.transform.rotate(pygame.transform.scale(
            beetle.subsurface(pygame.Rect(self.frameBeetle * self.frameSize, 0, self.frameSize, self.frameSize)),
            (self.size, self.size)), self.angle + 90)
        rotated_rect_beetle = rotated_beetle.get_rect(center=(self.x, self.y))
        screen.blit(rotated_beetle, rotated_rect_beetle)
        rotated_rect_collision = rotated_beetle.get_rect(center=(self.x + 50, self.y))
        vector_to_collision = pygame.math.Vector2(rotated_rect_collision.center) - pygame.math.Vector2(
            rotated_rect_beetle.center)
        rotated_rect_collision.center = rotated_beetle.get_rect(
            center=rotated_rect_beetle.center + vector_to_collision.rotate(-self.angle)).center
        self.collisionX, self.collisionY = rotated_rect_collision.center

    def rotate_beetle(self, x, y):
        dx = x - self.x
        dy = y - self.y
        self.angle = -math.atan2(dy, dx) * 180 / math.pi


class Fruit:
    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.size = 25
        self.speed = 2
        self.frameX = random.randint(0, 3)
        self.frameY = random.randint(0, 3)
        self.frameSize = 16
        self.delete = False

    def update(self):
        dx = screen_width / 2 - self.x
        dy = screen_height / 2 - self.y
        dist = (dx ** 2 + dy ** 2) ** 0.5
        if dist > 5:
            speed = 5
            self.x += dx / dist * speed
            self.y += dy / dist * speed
        else:
            self.delete = True
            global life
            life -= 1
            if life <= 0:
                global state
                global high_score
                state = 2
                if high_score < score:
                    high_score = score

    def draw(self):
        screen.blit(pygame.transform.scale(fruits.subsurface(
            pygame.Rect(self.frameX * self.frameSize, self.frameY * self.frameSize, self.frameSize, self.frameSize)),
            (self.size * 2, self.size * 2)),
            (self.x - self.size, self.y - self.size))


game = Game()
menu = Menu()
settings = Settings()
pause = Pause()


def fetch_file():
    global high_score
    global selected_background
    try:
        with open('data.txt', 'r') as f:
            contents = f.read()
            values = contents.split(',')
            high_score = int(values[0])
            selected_background = int(values[1])
    except FileNotFoundError:
        high_score = 0
        selected_background = 0

    animate()


def animate():
    running = True

    switch_to_game = True
    switch_to_menu = True
    switch_to_settings = True
    switch_to_pause = True

    while running:

        screen.blit(pygame.transform.scale(background[selected_background], (screen_width, screen_height)), (0, 0))

        mouse_x, mouse_y = pygame.mouse.get_pos()

        if state == 1:
            mouse_state = pygame.mouse.get_pressed()
            if mouse_state[0]:
                game.beetle.rotate_beetle(mouse_x, mouse_y)

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                with open('data.txt', 'w') as f:
                    f.write(f"{high_score},{selected_background}")
                running = False
            elif event.type == pygame.MOUSEBUTTONDOWN:
                if state == 1:
                    game.handle_click(mouse_x, mouse_y)
                elif state == 2:
                    menu.handle_click(mouse_x, mouse_y)
                elif state == 3:
                    settings.handle_click(mouse_x, mouse_y)
                elif state == 4:
                    pause.handle_click(mouse_x, mouse_y)

        if state == 1:
            if switch_to_game:
                if switch_to_pause:
                    game.reset_game()
                    pygame.mixer.music.load('Assets/Audio/game_sound.mp3')
                    pygame.mixer.music.play(-1)
                switch_to_game = False
            switch_to_menu = True
            switch_to_settings = True
            switch_to_pause = True
            game.update()
            game.draw()
            game.show_score()
        elif state == 2:
            if switch_to_menu:
                pygame.mixer.music.load('Assets/Audio/menu_sound.mp3')
                pygame.mixer.music.play(-1)
                switch_to_menu = False
            switch_to_game = True
            switch_to_settings = True
            switch_to_pause = True
            menu.render()
        elif state == 3:
            if switch_to_settings:
                switch_to_settings = False
            switch_to_game = True
            switch_to_menu = True
            switch_to_pause = True
            settings.render()
        elif state == 4:
            if switch_to_pause:
                switch_to_pause = False
            switch_to_game = True
            switch_to_menu = True
            switch_to_settings = True
            pause.render()

        pygame.display.flip()

        clock.tick(fps)


fetch_file()
