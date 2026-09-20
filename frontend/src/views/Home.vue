<template>
    <div class="home">
        <section class="hero-section">
            <section class="hero-image">
                <img src="../assets/images/home-background.png" id="hero-background" alt="hero image" />
            </section>

            <section class="hero-overlay">
                <h2 id="hero-title">Welcome Home</h2>
                <p id="des-hero">Meet the holy and the warm hands of the Praise community at Holy Trinity</p>
                <article class="overlay-bts">
                    <button id="bt-plan-visit">Plan Your Visit</button>
                    <button id="bt-sermons">Watch Sermons</button>
                </article>

                <article class="hero-cards">
                    <div id="cards">
                        <h3 id="est-number">+ 500</h3>
                        <p id="est-type">Members</p>
                    </div>
                    <div id="cards">
                        <h3 id="est-number">+ 700</h3>
                        <p id="est-type">Sermons</p>
                    </div>
                    <div id="cards">
                        <h3 id="est-number">+ 30</h3>
                        <p id="est-type">Locations</p>
                    </div>
                </article>

            </section>

        </section>


        <section class="announcements">
            <img src="../assets/images/events-bg.png" id="events-bg" alt="events background image" />
            <article class="act-panel">
                <img src="../assets/images/Marathon.jpeg" id="event-panel-bg" alt="event panel background" />
                <details id="act-info">
                    <p></p>
                </details>
            </article>

            <article class="upd-announcements">

                <nav class="announcements-nav">
                    <button><img src="../assets/images/notifications-icon.png" id="notifications-main-icon"
                            alt="announcement icon" />
                        <p>All</p>
                    </button>
                    <article class="updates-panel">
                        <button id="btn-announcements"></button>
                        <button id="btn-announcements"></button>
                        <button id="btn-announcements"></button>
                    </article>
                </nav>
                <article class="rc-announcements">

                    <article class="update-info">
                        <img src="../assets/images/announcements.png" id="notification-icon" alt="announcement icon" />
                        <article id="msg-text">
                            <h3>Announcement</h3>
                            <p></p>
                            <h4>thur, 18:45pm</h4>
                        </article>

                    </article>
                    <article class="update-info">

                        <img src="../assets/images/sermons-icon.png" id="notification-icon"
                            alt="sermons notification icon" />
                        <article id="msg-text">
                            <h3>Sermons</h3>
                            <p></p>
                            <h4>thur, 14:20pm</h4>
                        </article>

                    </article>
                    <article class="update-info">

                        <img src="../assets/images/events-icon.png" id="notification-icon"
                            alt="events notification icon" />
                        <article id="msg-text">
                            <h3>Events</h3>
                            <p></p>
                            <h4>tue, 10:15am</h4>
                        </article>
                    </article>
                </article>
            </article>
        </section>

        <aside class="events">
            <h2 id="events-title">Events</h2>
            <h2></h2>
            <h2></h2>
            <h2></h2>
        </aside>

        <aside class="events">
            <h2 id="events-title">Events</h2>
            <h2></h2>
            <h2></h2>
            <h2></h2>
        </aside>
        <article class="layout">
            <aside class="events-layout1">
                <h2 id="events-layout1-title">Events</h2>
                <h2></h2>
                <h2></h2>
                <h2></h2>
            </aside>
            <aside class="events-layout2">
                <h2 id="events-layout2-title">Events</h2>
                <h2></h2>
                <h2></h2>
                <h2></h2>
            </aside>
        </article>
    </div>

</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue'

const images = {
    img1: new URL("../assets/images/marathon.jpeg", import.meta.url).href,
    img2: new URL("../assets/images/welcome.jpeg", import.meta.url).href,
    img3: new URL("../assets/images/Hike.jpeg", import.meta.url).href,
    img4: new URL("../assets/images/Campus-meeting.jpeg", import.meta.url).href,
    img5: new URL("../assets/images/Gathering.jpeg", import.meta.url).href,
}

const eventType = {
    marathon: document.querySelector("marathon-tag"),
    gathering: document.querySelector("gathering-tag"),
    yFriday: document.querySelector("yFriday-tag")
}


</script>

<style>
.home {
    color: black;
}

.test {
    color: black;
}

.hero-section {
    position:relative;
    display: flex;
    flex-direction:column;
    width: 100%;
    height: 100dvh;
    justify-content:space-around;
    top:0;
}

.hero-image {
    display: absolute;
    position: relative;
    max-width: 100dvw;
    height: 105dvh;
    overflow: hidden;
    top: 0;
    left: 0;
}


#hero-background {
    position: absolute;
    width: 100%;
    height: 100dhv;
    object-fit: cover;
    filter: blur(1px) brightness(50%) saturate(120%);
    animation: zoom linear both;
    animation-timeline: scroll(root);
    animation-range: 0px 50vh;
}

@keyframes zoom {
    from {
        transform: scale(1);
    }

    to {
        transform: scale(1.1);
    }

}

.hero-overlay {
    position: absolute;
    display: flex;
    flex-direction: column;
    gap: 1rem;
    padding: 20px 0 0 0;
    width: 100dvw;
    height: 85dvh;
    background: linear-gradient(to right, rgba(0, 0, 0, 0.468) 20%, rgba(174, 64, 64, 0));


    #hero-title {
        font-family: "League Gothic", sans-serif;
        font-size: 4rem;
        font-weight: 700;
        font-display: block;
        letter-spacing: 5px;
        color: white;
        padding: 0 0 0 5vh;
        animation: visibility linear both;
        animation-timeline: scroll(root);
        animation-range: 0px 10vh;

    }

    #des-hero {
        font-family: sans-serif;
        font-size: 2rem;
        font-weight: lighter;
        padding: 0 0 0 5vh;
        color: white;
        width: 110vh;
        animation: visibility linear both;
        animation-timeline: scroll(root);
        animation-range: 0px 30vh;

    }

    .overlay-bts {
        position: relative;
        display: flex;
        gap: 2rem;
        top: 3rem;
        left: 2rem;
        animation: visibility linear both;
        animation-timeline: scroll(root);
        animation-range: 0px 60vh;


        #bt-plan-visit {
            position: relative;
            width: 18dvw;
            height: 10dvh;
            border: none;
            border-radius: 20px;
            font-size: 1.5rem;
            font-weight: 400;
            background-color: #e3901cbf;
            color: white;
        }

        #bt-sermons {
            position: relative;
            width: 18dvw;
            height: 10dvh;
            border: none;
            border-radius: 20px;
            font-size: 1.5rem;
            font-weight: 400;
            backdrop-filter: 4px;
            border: 1px solid white;
            background-color: #c6c5c514;
            color: white;
        }
    }

    .hero-cards {
        position: relative;
        display: flex;
        justify-content: space-around;
        width: 100dvh;
        height: 15dvh;
        top: 20vh;
        align-self: center;



        #cards {
            display: flex;
            flex-direction: column;
            border: solid 1px white;
            border-radius: 20px;
            width: 30dvw;
            height: 15dvh;
            backdrop-filter: 20px;
            background-color: rgba(0, 0, 0, 0.139);
            text-align: center;
            overflow: hidden;
            justify-content: center;
            transition: transform 0.3s cubic-bezier(0.25, 1, 0.5, 1), 0.3s ease;
            animation: visibility linear both;
            animation-timeline: scroll(root);
            animation-range: 0px 90vh;



            #est-number {
                font-family: sans-serif;
                font-size: 3rem;
                font-weight: 530;
                color: white;
            }

            #est-type {
                font-family: sans-serif;
                font-size: 2rem;
                font-weight: 100;
                color: white;
                align-self: center;
            }


        }
    }



}



#cards:hover {
    cursor: pointer;
    transform: scale(1.05);
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.15);
    z-index: 10;
}

@keyframes visibility {
    from {
        opacity: 100%;
    }

    to {
        opacity: 0%;
    }

}

.announcements {
    position: relative;
    top: -5rem;
    display: flex;
    width: 100dvw;
    height: 80dvh;
    padding: 0 1rem 0 1rem;
    overflow: hidden;
    justify-self: center;
    justify-content: space-between;
    align-items: center;

    #events-bg {
        position: absolute;
        width: 100%;
        height: 80dvh;
        object-fit: fill;
        left: 0;
        filter: blur(0.5px) brightness(80%) saturate(120%);
        animation: slide linear both;
        animation-timeline: scroll(root);
        animation-range: 0px 90vh;


    }


    .act-panel {
        position: relative;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        width: 60dvw;
        height: 65dvh;
        border: 0.5px solid rgb(255, 255, 255);
        border-radius: 20px;
        background-color: rgb(19, 19, 19);
        overflow: hidden;

        #events-panel-bg {
            position: relative;
            object-fit: cover;
            width: 100%;
            height: auto;
            top: 25vh;
            left: 0;
        }

        #act-info {
            position: absolute;
            background-color: rgba(6, 6, 6, 0.528);
            color: rgb(251, 248, 248);
            bottom: 0;
            width: 100dvw;
            height: 25dvh;
            justify-self: baseline;
        }




    }

    .upd-announcements {
        position: relative;
        display: flex;
        flex-direction: column;
        width: 35dvw;
        height: 65dvh;
        backdrop-filter: 42px;
        border-radius: 20px;
        background: linear-gradient(to bottom, rgba(0, 0, 0, 0), rgba(8, 8, 8, 0.427));

        .announcements-nav {
            display: flex;
            width: 35dvw;
            height: 10vh;
            gap: 1rem;
            border: solid 1px rgba(253, 252, 252, 0.249);
            border-radius: 20px;
            overflow: hidden;
            padding: 0 0 0 1rem;
            background-color: rgba(5, 5, 5, 0.066);
            align-items: center;

            button {
                display: flex;
                width: 5vw;
                height: 6vh;
                border-radius: 40px;
                font-size: 1.1rem;
                font-family: sans-serif;
                font-weight: 200;
                align-items: center;
                gap: 0.1rem;
                justify-content: center;
                box-shadow: inset rgba(0, 0, 0, 0.247);
            }

        }

        #notifications-main-icon {
            position: relative;
            object-fit: cover;
            width: 1.3rem;
            height: 1.3rem;

        }

        .updates-panel {
            display: flex;
            color: rgb(1, 1, 1);
            font-family: sans-serif;
            justify-content: space-around;
            width: 30vw;
            height: auto;
            gap: 0.6rem;
            font-size: 1.3rem;
            font-weight: 500;

            button {
                display: flex;
                width: 8vw;
                height: 6vh;
                border-radius: 40px;
                font-size: 1.1rem;
                font-family: sans-serif;
                font-weight: 200;
                align-items: center;
                background-color: rgba(0, 0, 0, 0.348);
                gap: 0.1rem;
                justify-content: center;
                box-shadow: inset rgba(0, 0, 0, 0.247);
            }

        }

        .rc-announcements {
            position: relative;
            display: flex;
            top: 0rem;
            flex-direction: column;
            justify-content: space-evenly;
            height: 45dvh;

            .update-info {
                display: flex;
                align-self: center;
                border: solid 1px rgb(255, 253, 253);
                background: linear-gradient(to right, rgba(0, 0, 0, 0.56), rgba(0, 0, 0, 0));
                color: white;
                padding: 0 0 0 1rem;
                gap: 1rem;
                border-radius: 20px;
                backdrop-filter: 10px;
                height: 12dvh;
                width: 33dvw;
                align-items: center;

                #notification-icon {
                    position: relative;
                    width: 2rem;
                    height: 2rem;
                }

                h3 {
                    font-family: sans-serif;
                    font-size: 1.5rem;
                    font-weight: 200;
                    color: white;

                }

                h4 {
                    font-family: sans-serif;
                    font-size: 0.8rem;
                    font-weight: 100;
                    color: #a7a7a7fc;
                }

            }

            .update-info:hover {
                cursor: pointer;
                transform: scale(1.05);
                box-shadow: 0 10px 30px rgba(0, 0, 0, 0.452);
                background: linear-gradient(to top, rgba(0, 0, 0, 0.588), rgba(0, 0, 0, 0));
                transition: .5s;
                backdrop-filter: 15px;
                z-index: 10;

            }
        }

    }

}

.events {
    display: flexbox;
    border: 1px solid black;
    border-radius: 20px;
    width: 80dvw;
    height: 20dvh;
    justify-self: center;


    #events-text {
        position: relative;
        color: black;
        font-size: 1rem;
        font-weight: 500;
        text-align: left;
    }
}

.layout {
    display: flex;
    overflow: hidden;
    border: 1px solid blue;
    width: 80dvw;
    height: 40dvh;
    justify-self: center;
}

.events-layout1 {
    display: flexbox;
    border: 1px solid black;
    border-radius: 20px;
    width: 60dvw;
    height: 40dvh;
    justify-self: center;
    flex: 2;

    #events-layout1-text {
        position: relative;
        color: black;
        font-size: 1rem;
        font-weight: 500;
        text-align: left;
    }
}

.events-layout2 {
    display: flexbox;
    border: 1px solid black;
    border-radius: 20px;
    width: 40dvw;
    height: 40dvh;
    justify-self: center;
    flex: 1;

    #events-layout2-text {
        position: relative;
        color: black;
        font-size: 1rem;
        font-weight: 500;
        text-align: left;
    }
}
</style>