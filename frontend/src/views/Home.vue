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

                <article class="banners">
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
            <h2 id="announcements-title">
                Announcements
            </h2>
            <h2></h2>
            <h2></h2>
            <h2></h2>
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

<style>
.home {
    color: black;
}

.test {
    color: black;
}

.hero-section {
    display: grid;
    width: 100%;
}

.hero-image {
    display: flex;
    position: relative;
    max-width: 100dvw;
    height: 101dvh;
    overflow: hidden;
    top: -11dvh;
    left: 0;
}


#hero-background {
    display: flex;
    position: absolute;
    width: 100%;
    height: auto;
    object-fit: fill;
    filter: blur(1px) brightness(50%) saturate(120%);
    margin: 0 0 60vh 0;
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
    height: 90dvh;
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

    .banners {
        position: relative;
        display: flex;
        justify-content: space-around;
        width: 95dvw;
        height: 20dvh;
        top: 7rem;
        align-self: center;
        

        #cards {
            display: flex;
            flex-direction: column;
            border: solid 1px white;
            border-radius: 20px;
            width: 30dvw;
            height: 20dvh;
            backdrop-filter: 20px;
            background-color: rgba(0, 0, 0, 0.139);
            text-align: center;
            overflow: hidden;
            justify-content: center;
            transition: transform 0.3s cubic-bezier(0.25, 1, 0.5, 1), box-shadow 0.3s ease;


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
    display: flexbox;
    border: 1px solid black;
    border-radius: 20px;
    width: 80dvw;
    height: 40dvh;
    justify-self: center;

    #announcements-text {
        position: relative;
        color: black;
        font-size: 1rem;
        font-weight: 500;
        text-align: left;
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